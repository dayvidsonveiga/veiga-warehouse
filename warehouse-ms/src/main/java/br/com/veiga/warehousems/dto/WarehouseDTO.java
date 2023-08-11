package br.com.veiga.warehousems.dto;

import lombok.Data;

@Data
public class WarehouseDTO {

    private Long id;

    private Long productId;

    private int quantity;

}
