package com.gamzatovgs.shop.dao.product;

import com.gamzatovgs.shop.entity.Item;
import com.gamzatovgs.shop.entity.Product;
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
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("from Product", Product.class);
        List<Product> allProducts = query.getResultList();

        return allProducts;
    }

    @Override
    public Product getProduct(long id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);

        return product;
    }

    @Override
    public Product getProductFromItemByItemId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session
                .createSQLQuery("select id, name, price from products as p where p.id = (select product_id from items as i where i.id = :id)")
                .setParameter("id", Long.valueOf(id))
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("name", StandardBasicTypes.STRING)
                .addScalar("price", StandardBasicTypes.BIG_DECIMAL)
                .setResultTransformer(Transformers.aliasToBean(Product.class));

        Product product = query.getSingleResult();

        return product;
    }

    @Override
    public void saveProduct(Product product, long saleId) {
        Session session = sessionFactory.getCurrentSession();

        Item item = product.getItems().get(0);

        NativeQuery query = session
                .createSQLQuery("insert into items(count, cost, product_id, sale_id) values (:count, :cost, :productId, :saleId)")
                .setParameter("count", item.getCount())
                .setParameter("cost", item.getCost())
                .setParameter("productId", product.getId())
                .setParameter("saleId", saleId);
        query.executeUpdate();
    }
}
