package com.nnk.springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  CurvePointDTO is the object of the view which makes the link with the CurvePoint model
 * @See CurvePoint
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurvePointDTO {

    private Integer id;

    @NotNull(message = "Password is mandatory")
    private Integer curveId;

    @NotNull(message = "Password is mandatory")
    private Double term;

    @NotNull(message = "Password is mandatory")
    @Digits(fraction = 2, integer = 10)
    private Double value;


}
