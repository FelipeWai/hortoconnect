package com.horto.backend.core.usecases.size.get;

import com.horto.backend.core.entities.Size;

import java.util.List;

public interface GetAllSizesCase {

    List<Size> execute();

}
