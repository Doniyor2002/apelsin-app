package uz.pdp.springbootapelsinffull.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private BigDecimal amount;
    private Integer invoiceId;
}
