package com.horto.backend.core.usecases.size.get;

import com.horto.backend.core.entities.Size;
import com.horto.backend.core.gateway.SizeGateway;

import java.util.List;

public class GetAllSizesByIdCaseImpl implements GetAllSizesByIdCase {

    private final SizeGateway sizeGateway;

    public GetAllSizesByIdCaseImpl(SizeGateway sizeGateway) {
        this.sizeGateway = sizeGateway;
    }

    @Override
    public List<Size> execute(List<Long> ids) {
        return sizeGateway.getAllSizesById(ids);
    }
}
