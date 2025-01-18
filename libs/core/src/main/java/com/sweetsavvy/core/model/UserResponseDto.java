package com.sweetsavvy.core.model;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        String address,
        String phoneNo,
        Boolean isActive
) {
}
