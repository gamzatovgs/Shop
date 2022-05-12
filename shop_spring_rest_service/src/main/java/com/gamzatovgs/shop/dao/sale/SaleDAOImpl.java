package com.gamzatovgs.shop.dao.sale;

import com.gamzatovgs.shop.entity.Item;
import com.gamzatovgs.shop.entity.Sale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAOImpl implements SaleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Sale> getAllSales() {
        Session session = sessionFactory.getCurrentSession();
        Query<Sale> query = session.createQuery("from Sale", Sale.class);
        List<Sale> allSales = query.getResultList();

        return allSales;
    }

    @Override
    public Sale getSale(long id) {
        Session session = sessionFactory.getCurrentSession();
        Sale sale = session.get(Sale.class, id);

        return sale;
    }

    @Override
    public Sale getSaleFromItemByItemId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Sale> query = session
                .createSQLQuery("select id, date from sales as s where s.id = (select sale_id from items as i where i.id = :id)")
                .setParameter("id", Long.valueOf(id))
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("date", StandardBasicTypes.TIMESTAMP)
                .setResultTransformer(Transformers.aliasToBean(Sale.class));
        Sale sale = query.getSingleResult();

        return sale;
    }

    public void saveSale(Sale sale) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(sale);
    }

    @Override
    public void saveSale(Sale sale, long productId) {
        Session session = sessionFactory.getCurrentSession();

        Item item = sale.getItems().get(0);

        NativeQuery query = session
                .createSQLQuery("insert into items(count, cost, product_id, sale_id) values (:count, :cost, :productId, :saleId)")
                .setParameter("count", item.getCount())
                .setParameter("cost", item.getCost())
                .setParameter("productId", productId)
                .setParameter("saleId", sale.getId());
        query.executeUpdate();
    }
}
