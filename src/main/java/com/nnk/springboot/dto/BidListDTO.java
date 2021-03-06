package com.nnk.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *  BidlistDTO is the object of the view which makes the link with the BidList model
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidListDTO {


    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    private String account;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @Digits( fraction = 2, integer = 10, message="Invalid number ( 10 integer max with 2 fraction max )")
    @Min(value = 0 , message ="must be positif")
    private Double bidQuantity;

   //----------Constructor-----------------------------------------------------------------------------------
    public BidListDTO(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
