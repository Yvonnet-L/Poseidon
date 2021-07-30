package com.ly.p7.Poseidon.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *  Class CurvePoint which allows the link with the curvepoint table of the DB
 */

@Entity
@Table(name = "curvepoint")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private LocalDateTime asOfDate;

    @Column(name = "term")
    private Double term;

    @Column(name = "value")
    private Double value;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    //------------------ Constructor -------------------------------------------------------



}
