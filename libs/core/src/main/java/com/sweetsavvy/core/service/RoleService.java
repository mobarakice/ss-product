package com.sweetsavvy.core.service;

import com.sweetsavvy.core.entity.RoleEntity;
import com.sweetsavvy.core.model.RoleRequestDto;

public interface RoleService {

    RoleEntity save(RoleRequestDto roleRequestDto);

    RoleEntity findById(Long ids);
}
