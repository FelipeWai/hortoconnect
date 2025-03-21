package com.horto.backend.core.usecases.user.get;

import com.horto.backend.core.entities.User;

public interface GetUserByPhoneCase {

    User execute(String phone);

}
