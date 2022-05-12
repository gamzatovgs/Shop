package com.gamzatovgs.shop.service.item;

import com.gamzatovgs.shop.dao.item.ItemDAO;
import com.gamzatovgs.shop.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDAO itemDAO;

    @Override
    @Transactional
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Override
    @Transactional
    public Item getItem(long id) {
        return itemDAO.getItem(id);
    }

    @Override
    @Transactional
    public void saveItem(Item item) {
        itemDAO.saveItem(item);
    }
}
