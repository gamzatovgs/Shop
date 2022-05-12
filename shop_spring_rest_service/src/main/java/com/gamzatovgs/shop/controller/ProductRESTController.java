package com.gamzatovgs.shop.controller;

import com.gamzatovgs.shop.entity.Product;
import com.gamzatovgs.shop.entity.Sale;
import com.gamzatovgs.shop.service.product.ProductService;
import com.gamzatovgs.shop.service.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductRESTController {
    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @GetMapping("/products")
    public List<Product> showAllProducts() {
        List<Product> allProducts = productService.getAllProducts();

        return allProducts;
    }

    @GetMapping("/products/{id}")
    public Product getProductFromItemByItemId(@PathVariable long id) {
        Product product = productService.getProductFromItemByItemId(id);

        return product;
    }

    @PostMapping("/products")
    public Product addNewProduct(@RequestBody Product product) {
        Sale sale = new Sale();
        sale.setDate(new Timestamp(new Date().getTime()));

        saleService.saveSale(sale);
        productService.saveProduct(product, sale.getId());

        return product;
    }
}
