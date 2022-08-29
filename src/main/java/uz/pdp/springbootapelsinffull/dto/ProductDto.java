package uz.pdp.springbootapelsinffull.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private String name;
    private Double price;
    private String photo;
    private String description;
    private Integer catId;
}
