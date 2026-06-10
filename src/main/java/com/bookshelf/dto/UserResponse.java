package com.bookshelf.dto;

import com.bookshelf.entity.User;

public record UserResponse(
        Long id,
        String username,
        String email
) {
    public static UserResponse from(User user){
        return new UserResponse(user.getId(),user.getUsername(), user.getEmail());
    }
}
