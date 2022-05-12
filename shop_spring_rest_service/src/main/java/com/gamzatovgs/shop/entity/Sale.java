package com.gamzatovgs.shop.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @SequenceGenerator(name = "salesIdSeq", sequenceName = "sales_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salesIdSeq")
    @Column(name = "id")
    private long id;

    @Column(name = "date")
    private Timestamp date;

    @OneToMany(fetch = FetchType.EAGER
            , cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private List<Item> items;

    public Sale() {
    }

    public Sale(long id, Timestamp date) {
        this.id = id;
        this.date = date;
    }

    public void addItemToSale(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
