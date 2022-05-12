package com.gamzatovgs.shop.service.sale;

import com.gamzatovgs.shop.dao.sale.SaleDAO;
import com.gamzatovgs.shop.entity.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDAO saleDAO;

    @Override
    @Transactional
    public List<Sale> getAllSales() {
        return saleDAO.getAllSales();
    }

    @Override
    @Transactional
    public Sale getSale(long id) {
        return saleDAO.getSale(id);
    }

    @Override
    @Transactional
    public Sale getSaleFromItemByItemId(long id) {
        return saleDAO.getSaleFromItemByItemId(id);
    }

    @Override
    @Transactional
    public void saveSale(Sale sale) {
        saleDAO.saveSale(sale);
    }

    @Override
    @Transactional
    public void saveSale(Sale sale, long productId) {
        saleDAO.saveSale(sale, productId);
    }
}
