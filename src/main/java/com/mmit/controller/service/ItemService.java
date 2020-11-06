package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Category;
import com.mmit.model.entity.Item;

public class ItemService {
	private EntityManager em;
	public ItemService(EntityManager em) {
		this.em=em;
	}
	public List<Item> findAll() {
		TypedQuery<Item>query=em.createNamedQuery("Item.getAll", Item.class);
		return query.getResultList();
		
	}
	public void save(Item item) {
		em.getTransaction().begin();
		if(item.getId()==0) {//new category
			em.persist(item);
		}
		else {
			em.merge(item);//edit category
		}
		em.getTransaction().commit();
		
	}
	public Item findByid(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}
	public void delete(int id) {
		em.getTransaction().begin();
		Item item=em.find(Item.class, id);
		em.remove(id);
		em.getTransaction().commit();
	}
}
