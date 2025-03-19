package com.horto.backend.core.usecases.size.get;

import com.horto.backend.core.entities.Size;

public interface GetSizeByNameCase {

    Size execute(String name);

}
