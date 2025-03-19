package com.horto.backend.core.usecases.size.get;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.gateway.SizeGateway;

import java.util.List;

public class GetAllSizesCaseImpl implements GetAllSizesCase {

    private final SizeGateway sizeGateway;

    public GetAllSizesCaseImpl(SizeGateway sizeGateway) {
        this.sizeGateway = sizeGateway;
    }

    @Override
    public List<Size> execute() {
        return sizeGateway.getAllSizes();
    }
}
