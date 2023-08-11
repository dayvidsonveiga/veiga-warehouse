package br.com.veiga.warehousems.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_warehouse")
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_product", nullable = false)
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

}
