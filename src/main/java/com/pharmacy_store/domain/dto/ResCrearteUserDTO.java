package com.pharmacy_store.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResCrearteUserDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String email;

}
