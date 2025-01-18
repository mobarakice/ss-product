package com.sweetsavvy.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "roles")
public class RoleEntity extends BaseEntity {
    private String roleName;
}
