package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.exceptions.product.ProductAlreadyExists;
import com.horto.backend.core.exceptions.product.ProductNotFoundException;
import com.horto.backend.core.gateway.ProductGateway;
import com.horto.backend.core.usecases.category.get.GetCategoryByIdCase;
import com.horto.backend.core.usecases.productPicture.post.CreateProductPicturesCase;
import com.horto.backend.core.usecases.subcategory.get.GetSubcategoryByIdCase;
import com.horto.backend.infra.config.aws.s3.ImageOptimizationService;
import com.horto.backend.infra.config.aws.s3.S3StorageService;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.filters.product.ProductFilter;
import com.horto.backend.infra.filters.product.ProductSpecification;
import com.horto.backend.infra.mapper.ProductMapper;
import com.horto.backend.infra.mapper.SubcategoryMapper;
import com.horto.backend.infra.persistence.entities.ProductEntity;
import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import com.horto.backend.infra.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepoGateway implements ProductGateway {

    private final S3StorageService s3StorageService;

    private final ImageOptimizationService imageOptimizationService;

    private final CreateProductPicturesCase createProductPicturesCase;

    private final GetSubcategoryByIdCase getSubcategoryByIdCase;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final SubcategoryMapper subcategoryMapper;
    private final GetCategoryByIdCase getCategoryByIdCase;


    @Override
    public Page<Product> getAllProducts(ProductFilter filter, Pageable pageable) {
        if(filter.getCategory_id() != null){
            getCategoryByIdCase.execute(filter.getCategory_id());
        }
        if(filter.getSubcategory_id() != null){
            getSubcategoryByIdCase.execute(filter.getSubcategory_id());
        }

        ProductSpecification productSpecification = new ProductSpecification(filter);
        Page<ProductEntity> productEntities = productRepository.findAll(productSpecification, pageable);

        return productEntities.map(productMapper::toDomain);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<ProductEntity> entityOptional = productRepository.findById(id);
        if (entityOptional.isPresent()) {
            ProductEntity productEntity = entityOptional.get();
            return Optional.of(productMapper.toDomain(productEntity));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Product> getProductByName(String name) {
        String nameLowerCase = name.trim().toLowerCase();
        Optional<ProductEntity> product = productRepository.findByName(nameLowerCase);
        if (product.isPresent()) {
            ProductEntity productEntity = product.get();
            return Optional.of(productMapper.toDomain(productEntity));
        }
        return Optional.empty();
    }

    @Override
    public Product createProduct(ProductRequestDTO requestDTO, List<MultipartFile> pictures) {
        String nameLowerCase = requestDTO.name().trim().toLowerCase();
        if(getProductByName(nameLowerCase).isPresent()) {
            throw new ProductAlreadyExists(nameLowerCase);
        }

        Subcategory subcategory = getSubcategoryByIdCase.execute(requestDTO.subcategory_id());

        ProductEntity productEntity = productMapper.toEntity(requestDTO);
        productEntity.setName(nameLowerCase);
        productEntity.setSubcategory(subcategoryMapper.toEntity(subcategory));

        ProductEntity savedEntity = productRepository.save(productEntity);

        if(pictures != null) {
            List<ProductPicture> pictureList = pictures.stream().map(picture -> {

                try {

                    byte[] optimizedImage = imageOptimizationService.optimizeImage(picture);
                    MultipartFile optimizedFile = imageOptimizationService.convertToMultipartFile(optimizedImage, picture.getOriginalFilename());

                    String url = s3StorageService.uploadFile(optimizedFile);

                    return new ProductPicture(null, url, productMapper.toDomain(savedEntity));
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao otimizar imagem", e);
                }

//                String url = s3StorageService.uploadFile(picture);
//                return new ProductPicture(null, url, productMapper.toDomain(savedEntity));
            }).toList();

            createProductPicturesCase.execute(pictureList);
        }

        return productMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteProductById(Long id) {
        if(getProductById(id).isPresent()) {
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException(id.toString());
        }
    }

    @Override
    public Product patchProductById(Long id, ProductPatchDTO patchDTO) {
        Product productToUpdate = getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        ProductEntity productEntity = productMapper.toEntity(productToUpdate);

        if(patchDTO.name().isPresent()){
            if(!patchDTO.name().get().equals(productEntity.getName())){
                if(getProductByName(patchDTO.name().get()).isPresent()) {
                    throw new ProductAlreadyExists(patchDTO.name().get());
                }
                productEntity.setName(patchDTO.name().get());
            }else{
                throw new ProductAlreadyExists(patchDTO.name().get());
            }
        }

        if(patchDTO.subcategory_id().isPresent()) {
            if(!patchDTO.subcategory_id().get().equals(productEntity.getId())){
                SubcategoryEntity subcategoryEntity = subcategoryMapper.toEntity(getSubcategoryByIdCase.execute(patchDTO.subcategory_id().get()));
                productEntity.setSubcategory(subcategoryEntity);
            }
        }

        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDomain(savedEntity);
    }
}
