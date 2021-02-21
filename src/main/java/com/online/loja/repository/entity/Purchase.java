package com.online.loja.repository.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Transient
    private BigDecimal price;

    @NotNull
    @NotBlank
    private String status;

    @NotNull
    @NotBlank
    private String emailUser;

    @OneToOne
    @NotNull
    @NotBlank
    private Address address;

    @ManyToMany
    @NotNull
    @NotBlank
    private List<Product> products;

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;


    public BigDecimal getCalculatePrice(List<Product> products) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Product prod : products){
            totalPrice.add(prod.getPrice());
        }
        return totalPrice;
    }
}
