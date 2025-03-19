package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.exceptions.size.SizeAlreadyExists;
import com.horto.backend.core.gateway.SizeGateway;
import com.horto.backend.infra.dto.size.request.SizeRequestDTO;
import com.horto.backend.infra.mapper.SizeMapper;
import com.horto.backend.infra.persistence.entities.SizeEntity;
import com.horto.backend.infra.persistence.repositories.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SizeRepoGateway implements SizeGateway {

    private final SizeRepository sizeRepository;

    private final SizeMapper sizeMapper;

    @Override
    public List<Size> getAllSizes() {
        List<SizeEntity> entityList = sizeRepository.findAll();
        return entityList.stream()
                .map(sizeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Size> getSizeById(Long id) {
        Optional<SizeEntity> sizeOptional = sizeRepository.findById(id);
        if (sizeOptional.isPresent()) {
            return Optional.of(sizeMapper.toDomain(sizeOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Size> getSizeByName(String name) {
        Optional<SizeEntity> sizeEntityOptional = sizeRepository.findByName(name);
        if (sizeEntityOptional.isPresent()) {
            return Optional.of(sizeMapper.toDomain(sizeEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Size createSize(SizeRequestDTO size) {
        if(getSizeByName(size.name().trim().toLowerCase()).isPresent()){
            throw new SizeAlreadyExists(size.name());
        }

        SizeEntity entity = sizeMapper.toEntity(size);
        entity.setName(size.name().trim().toLowerCase());
        SizeEntity savedEntity = sizeRepository.save(entity);
        return sizeMapper.toDomain(savedEntity);
    }

    @Override
    public List<Size> getAllSizesById(List<Long> ids) {
        List<SizeEntity> entityList = sizeRepository.findAllByIdIn(ids);
        return entityList.stream()
                .map(sizeMapper::toDomain)
                .collect(Collectors.toList());
    }
}
