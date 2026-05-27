package com.bookshelf.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank(message = "username required") String username,
        @NotBlank @Email(message = "Not correct email") String email,
        @NotBlank(message = "password required") String password

){}
