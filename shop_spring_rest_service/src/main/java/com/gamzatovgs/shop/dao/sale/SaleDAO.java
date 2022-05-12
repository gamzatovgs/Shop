package com.gamzatovgs.shop.dao.sale;

import com.gamzatovgs.shop.entity.Sale;

import java.util.List;

public interface SaleDAO {
    public List<Sale> getAllSales();

    public Sale getSale(long id);

    public Sale getSaleFromItemByItemId(long id);

    public void saveSale(Sale sale);

    public void saveSale(Sale sale, long saleId);
}
