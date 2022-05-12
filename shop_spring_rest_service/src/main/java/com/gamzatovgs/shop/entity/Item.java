package com.gamzatovgs.shop.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @SequenceGenerator(name = "itemsIdSeq", sequenceName = "items_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemsIdSeq")
    @Column(name = "id")
    private long id;

    @Column(name = "count")
    private long count;

    @Column(name = "cost")
    private BigDecimal cost;

    public Item() {
    }

    public Item(long id, long count, BigDecimal cost) {
        this.id = id;
        this.count = count;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
