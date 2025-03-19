package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;

import java.util.List;

public interface GetAllSubcategoriesCase {

    List<Subcategory> execute();

}
