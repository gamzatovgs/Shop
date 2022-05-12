package com.gamzatovgs.shop.controller;

import com.gamzatovgs.shop.entity.Sale;
import com.gamzatovgs.shop.service.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SaleRESTController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/sales")
    public List<Sale> showAllSales() {
        List<Sale> allSales = saleService.getAllSales();

        return allSales;
    }

    @GetMapping("/sales/{id}")
    public Sale getSaleFromItemByItemId(@PathVariable long id) {
        Sale sale = saleService.getSaleFromItemByItemId(id);

        return sale;
    }

    @PostMapping("/sales")
    public Sale addNewSale(@RequestBody Sale sale) {
        long productId = sale.getItems().get(0).getId();

        saleService.saveSale(sale, productId);

        return sale;
    }

    @PutMapping("/sales")
    public Sale updateSale(@RequestBody Sale sale) {
        saleService.saveSale(sale);

        return sale;
    }
}
