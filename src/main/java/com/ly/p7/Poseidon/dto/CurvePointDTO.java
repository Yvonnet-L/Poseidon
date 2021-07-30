package com.ly.p7.Poseidon.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  CurvePointDTO is the object of the view which makes the link with the CurvePoint model
 * @See CurvePoint
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurvePointDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer curveId;

    private Double term;

    private Double value;


}
