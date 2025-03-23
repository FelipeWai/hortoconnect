package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;

import java.util.List;

public interface GetSubcatByCatIdCase {

    List<Subcategory> execute(Long categoryId);

}
