package com.horto.backend.infra.filters.offer;

import com.horto.backend.infra.persistence.entities.SupplierOfferEntity;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OfferSpecification implements Specification<SupplierOfferEntity> {

    private final Long productId;
    private final OfferFilter filter;

    @Override
    public Predicate toPredicate(Root<SupplierOfferEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // join nas entidades relacionadas se precisar
        Join<Object, Object> productJoin = root.join("product", JoinType.INNER);
        Join<Object, Object> qualityJoin = root.join("quality", JoinType.LEFT);
        Join<Object, Object> sizeJoin = root.join("size", JoinType.LEFT);

        // Filtro obrigatório por produto
        predicates.add(cb.equal(productJoin.get("id"), productId));

        // Filtro opcional por qualidade
        if (filter.getQualityId() != null) {
            predicates.add(cb.equal(qualityJoin.get("id"), filter.getQualityId()));
        }

        // Filtro opcional por tamanho
        if (filter.getSizeId() != null) {
            predicates.add(cb.equal(sizeJoin.get("id"), filter.getSizeId()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}