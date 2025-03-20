package com.horto.backend.core.usecases.size.delete;

import com.horto.backend.core.gateway.SizeGateway;

public class DeleteSizeByIdCaseImpl implements DeleteSizeByIdCase {

    private final SizeGateway sizeGateway;

    public DeleteSizeByIdCaseImpl(SizeGateway sizeGateway) {
        this.sizeGateway = sizeGateway;
    }

    @Override
    public void execute(Long id) {
        sizeGateway.deleteSizeById(id);
    }
}
