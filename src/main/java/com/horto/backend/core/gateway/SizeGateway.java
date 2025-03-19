package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Size;
import com.horto.backend.infra.dto.size.request.SizeRequestDTO;

import java.util.List;
import java.util.Optional;

public interface SizeGateway {

    List<Size> getAllSizes();

    Optional<Size> getSizeById(Long id);

    Size createSize(SizeRequestDTO size);

    Optional<Size> getSizeByName(String name);
}
