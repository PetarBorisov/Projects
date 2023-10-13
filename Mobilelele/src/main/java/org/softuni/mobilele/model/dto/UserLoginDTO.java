package org.softuni.mobilele.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserLoginDTO (String email,
                            String password) {
}
