package com.horto.backend.core.usecases.size.get;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.exceptions.size.SizeNotFoundException;
import com.horto.backend.core.gateway.SizeGateway;

public class GetSizeByIdCaseImpl implements GetSizeByIdCase {

    private final SizeGateway sizeGateway;

    public GetSizeByIdCaseImpl(SizeGateway sizeGateway) {
        this.sizeGateway = sizeGateway;
    }

    @Override
    public Size execute(Long id) {
        return sizeGateway.getSizeById(id)
                .orElseThrow(() -> new SizeNotFoundException(id.toString()));
    }
}
