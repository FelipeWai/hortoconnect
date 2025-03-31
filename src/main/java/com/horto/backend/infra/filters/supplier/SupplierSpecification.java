package com.horto.backend.infra.filters.supplier;

import com.horto.backend.infra.persistence.entities.SupplierEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SupplierSpecification implements Specification<SupplierEntity> {

    private final SupplierFilter filter;

    @Override
    public Predicate toPredicate(Root<SupplierEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        query.distinct(true);

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