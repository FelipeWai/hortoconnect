package com.horto.backend.core.usecases.quality.get;

import com.horto.backend.core.entities.Quality;

import java.util.List;

public interface GetAllQualitiesByIdCase {
    List<Quality> execute(List<Long> ids);
}
