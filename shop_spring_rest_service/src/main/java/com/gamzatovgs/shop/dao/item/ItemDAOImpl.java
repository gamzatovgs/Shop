package com.gamzatovgs.shop.dao.item;

import com.gamzatovgs.shop.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Item> getAllItems() {
        Session session = sessionFactory.getCurrentSession();
        Query<Item> query = session.createQuery("from Item", Item.class);
        List<Item> allItems = query.getResultList();

        return allItems;
    }

    @Override
    public Item getItem(long id) {
        Session session = sessionFactory.getCurrentSession();
        Item item = session.get(Item.class, id);

        return item;
    }

    @Override
    public void saveItem(Item item) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(item);
    }
}
