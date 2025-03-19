package com.horto.backend.core.usecases.quality.get;

import com.horto.backend.core.entities.Quality;

public interface GetQualityByNameCase {

    Quality execute(String name);

}
