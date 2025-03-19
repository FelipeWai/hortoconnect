package com.horto.backend.core.usecases.size.post;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.gateway.SizeGateway;
import com.horto.backend.infra.dto.size.request.SizeRequestDTO;

public class CreateSizeCaseImpl implements CreateSizeCase {

    private final SizeGateway sizeGateway;

    public CreateSizeCaseImpl(SizeGateway sizeGateway) {
        this.sizeGateway = sizeGateway;
    }

    @Override
    public Size execute(SizeRequestDTO size) {
        return sizeGateway.createSize(size);
    }
}
