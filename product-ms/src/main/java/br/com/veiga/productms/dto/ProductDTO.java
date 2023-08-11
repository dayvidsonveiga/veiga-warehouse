package br.com.veiga.productms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class ProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 50)
    private String description;

    @Positive
    private double price;

    private boolean available;

}
