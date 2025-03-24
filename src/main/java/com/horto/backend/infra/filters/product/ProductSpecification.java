package com.horto.backend.infra.filters.product;

import com.horto.backend.infra.persistence.entities.ProductEntity;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductSpecification implements Specification<ProductEntity> {

    private final ProductFilter filter;

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        query.distinct(true);

        if (filter.getCategory_id() != null) {
            Join<Object, Object> subcategoryJoin = root.join("subcategory");
            Join<Object, Object> categoryJoin = subcategoryJoin.join("category");
            predicates.add(cb.equal(categoryJoin.get("id"), filter.getCategory_id()));
        }

        if (filter.getSubcategory_id() != null) {
            predicates.add(cb.equal(root.get("subcategory").get("id"), filter.getSubcategory_id()));
        }

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + filter.getName().toLowerCase() + "%"
            ));
        }

        return predicates.isEmpty() ?
                cb.conjunction() :
                cb.and(predicates.toArray(new Predicate[0]));
    }
}
