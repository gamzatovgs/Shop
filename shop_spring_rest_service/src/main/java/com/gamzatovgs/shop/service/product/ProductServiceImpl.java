package com.gamzatovgs.shop.service.product;

import com.gamzatovgs.shop.dao.product.ProductDAO;
import com.gamzatovgs.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public Product getProduct(long id) {
        return productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public Product getProductFromItemByItemId(long id) {
        return productDAO.getProductFromItemByItemId(id);
    }

    @Override
    @Transactional
    public void saveProduct(Product product, long saleId) {
        productDAO.saveProduct(product, saleId);
    }
}
