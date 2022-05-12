package com.gamzatovgs.shop.service.product;

import com.gamzatovgs.shop.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProduct(long id);

    public Product getProductFromItemByItemId(long id);

    public void saveProduct(Product product, long saleId);
}
