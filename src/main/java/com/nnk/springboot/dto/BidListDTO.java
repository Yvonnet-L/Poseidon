package com.nnk.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "biQuantity is mandatory")
    @Min(value = 0 )
    private Double bidQuantity;

   //----------Constructor-----------------------------------------------------------------------------------
    public BidListDTO(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }
}
