package com.horto.backend.core.usecases.size.get;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.exceptions.size.SizeNotFoundException;
import com.horto.backend.core.gateway.SizeGateway;

public class GetSizeByNameCaseImpl implements GetSizeByNameCase {

    private final SizeGateway sizeGateway;

    public GetSizeByNameCaseImpl(SizeGateway sizeGateway) {
        this.sizeGateway = sizeGateway;
    }

    @Override
    public Size execute(String name) {
        return sizeGateway.getSizeByName(name)
                .orElseThrow(() -> new SizeNotFoundException(name));
    }
}
