package com.sweetsavvy.core.service.impl;

import com.sweetsavvy.core.entity.RoleEntity;
import com.sweetsavvy.core.model.RoleRequestDto;
import com.sweetsavvy.core.repository.RoleRepository;
import com.sweetsavvy.core.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RoleEntity save(RoleRequestDto roleRequestDto) {
        var role = modelMapper.map(roleRequestDto, RoleEntity.class);
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity findById(Long ids) {
        return roleRepository.findById(ids).orElse(null);
    }
}
