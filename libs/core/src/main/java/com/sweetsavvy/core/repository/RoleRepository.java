package com.sweetsavvy.core.repository;

import com.sweetsavvy.core.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {
    Optional<RoleEntity> findByIdAndIsActiveTrue(Long id);

    List<RoleEntity> findByRoleNameAndIsActiveTrue(String roleName);

    Set<RoleEntity> findRoleByIdIn(Set<Long> ids);
}
