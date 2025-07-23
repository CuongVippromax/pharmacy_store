package com.pharmacy_store.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResLoginDTO {
    @NotBlank
    private String email;

    private String accessToken;
}
