package com.gamzatovgs.shop.dao.product;

import com.gamzatovgs.shop.entity.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getAllProducts();

    public Product getProduct(long id);

    public void saveProduct(Product product, long saleId);

    public Product getProductFromItemByItemId(long id);
}
