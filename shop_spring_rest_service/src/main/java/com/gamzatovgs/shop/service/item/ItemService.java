package com.gamzatovgs.shop.service.item;

import com.gamzatovgs.shop.entity.Item;

import java.util.List;

public interface ItemService {
    public List<Item> getAllItems();

    public Item getItem(long id);

    public void saveItem(Item item);
}
