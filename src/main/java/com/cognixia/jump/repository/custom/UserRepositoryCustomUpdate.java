package com.cognixia.jump.repository.custom;

import com.cognixia.jump.model.User;

public interface UserRepositoryCustomUpdate {
    User patch(Long id, String field, Object value);
}
