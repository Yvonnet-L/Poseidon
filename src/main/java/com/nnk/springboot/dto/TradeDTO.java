package com.nnk.springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *  TradeDTO is the object of the view which makes the link with the Trade model
 * @See Trade
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeDTO {

    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Account is mandatory")
    private String type;

    @Digits( fraction = 2, integer = 10, message="Invalid number ( 10 integer max with 2 fraction max )")
    @Min(value = 0 , message ="must be positif")
    private Double buyQuantity;

    //----------Constructor-----------------------------------------------------------------------------------
    

}
