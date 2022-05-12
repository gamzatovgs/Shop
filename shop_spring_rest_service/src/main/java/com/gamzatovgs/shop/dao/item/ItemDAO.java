package com.gamzatovgs.shop.dao.item;

import com.gamzatovgs.shop.entity.Item;

import java.util.List;

public interface ItemDAO {
    public List<Item> getAllItems();

    public Item getItem(long id);

    public void saveItem(Item item);
}
