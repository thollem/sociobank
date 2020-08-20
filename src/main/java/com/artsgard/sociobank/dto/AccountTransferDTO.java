package com.artsgard.sociobank.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author wdragstra
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccountTransferDTO implements Serializable {

    @NotNull
    private Long accountId;
   
    @NotNull
    private Long accountTransferId;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=12, fraction=2)
    private BigDecimal amount;
 
    private String description;
}
