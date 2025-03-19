package com.horto.backend.core.usecases.quality.post;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.infra.dto.quality.request.QualityRequestDTO;

public interface CreateQualityCase {

    Quality createQuality(QualityRequestDTO quality);

}
