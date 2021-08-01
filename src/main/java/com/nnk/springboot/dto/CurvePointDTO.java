package com.nnk.springboot.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

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

    @NotNull(message = "curveId is mandatory")
    private Integer curveId;

    @NotNull(message = "Term is mandatory")
    @Digits(fraction = 2, integer = 10)
    private Double term;

    @NotNull(message = "Value is mandatory")
    @Digits(fraction = 2, integer = 10)
    private Double value;

}
