package com.gamzatovgs.shop.controller;

import com.gamzatovgs.shop.entity.Item;
import com.gamzatovgs.shop.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ItemRESTController {
    @Autowired
    private ItemService itemService;

    @PutMapping("/items")
    public Item updateItem(@RequestBody Item item) {
        itemService.saveItem(item);

        return item;
    }
}
