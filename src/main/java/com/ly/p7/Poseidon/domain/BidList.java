package com.ly.p7.Poseidon.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer BidListId;
    @NotEmpty(message = "Account is mandatory")
    private String account;
    @NotBlank(message = "Type is mandatory")
    private String type;
    @NotNull(message = "biQuantity is mandatory")
    @Min(value = 0 )
    private Double bidQuantity;
    @NotNull(message = "askQuantity is mandatory")
    private Double askQuantity;
    @NotNull(message = "bid is mandatory")
    private Double bid;
    @NotNull(message = "ask is mandatory")
    private Double ask;
    @NotNull(message = "Benchmark is mandatory")
    private String benchmark;
    @NotNull(message = "BidListDate is mandatory")
    private Date bidListDate;
    @NotBlank(message = "Commentary is mandatory")
    private String commentary;
    @NotBlank(message = "Security is mandatory")
    private String security;
    @NotBlank(message = "Status is mandatory")
    private String status;
    @NotBlank(message = "Trader is mandatory")
    private String trader;
    @NotBlank(message = "Book is mandatory")
    private String book;
    @NotNull(message = "CreationName is mandatory")
    private String creationName;
    @NotNull(message = "CreationDate is mandatory")
    private Date creationDate;
    @NotNull(message = "RevisionName is mandatory")
    private String revisionName;
    @NotNull(message = "RevisionDate is mandatory")
    private Date revisionDate;
    @NotBlank(message = "DealName is mandatory")
    private String dealName;
    @NotBlank(message = "DealType is mandatory")
    private String dealType;
    @NotBlank(message = "SourceListId is mandatory")
    private String sourceListId;
    @NotBlank(message = "RevisionName is mandatory")
    private String side;


    public Integer getBidListId() {
        return BidListId;
    }

    public void setBidListId(Integer bidListId) {
        BidListId = bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Date getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Date bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
