package com.horto.backend.core.usecases.size.post;

import com.horto.backend.core.entities.Size;
import com.horto.backend.infra.dto.size.request.SizeRequestDTO;

public interface CreateSizeCase {

    Size execute(SizeRequestDTO size);

}
