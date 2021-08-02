package com.nnk.springboot.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *  Class Trade which allows the link with the trade table of the DB
 */


@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "trade_id")
    private Integer tradeId;

    private String account;

    private String type;

    @Column(name = "buy_quantity")
    private Double buyQuantity;

    @Column(name = "sell_quantity")
    private Double sellQuantity;

    @Column(name = "buy_price")
    private Double buyPrice;

    @Column(name = "sell_price")
    private Double sellPrice;

    private String benchmark;

    @Column(name = "trade_date")
    private LocalDateTime tradeDate;

    private String security;

    private String status;

    private String trader;

    private String book;

    @Column(name = "creation_name")
    private String creationName;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "revision_name")
    private String revisionName;

    @Column(name = "revision_date")
    private LocalDateTime revisionDate;

    @Column(name = "deal_name")
    private String dealName;

    @Column(name = "deal_type")
    private String dealType;

    @Column(name = "source_list_id")
    private String sourceListId;

    private String side;

    //------------------ Constructor -------------------------------------------------------

}
