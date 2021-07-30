package com.ly.p7.Poseidon.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  Class DidList which allows the link with the bitList table of the DB
 */

@Entity
@Table(name = "bidlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    private String account;

    private String type;

    @Column(name = "bid_quantity")
    private Double bidQuantity;

    @Column(name = "ask_quantity")
    private Double askQuantity;

    private Double bid;

    private Double ask;

    private String benchmark;

    @Column(name = "bid_list_date")
    private LocalDateTime bidListDate;

    private String commentary;

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

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public BidList(Integer bidListId, String account, String type, Double bidQuantity) {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    //--------------------------------------------------------------------------------------
   
}
