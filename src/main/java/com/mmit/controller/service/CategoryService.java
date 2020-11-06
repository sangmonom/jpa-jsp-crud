package com.mmit.controller.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Category;



public class CategoryService {
	private EntityManager em;
	

	public CategoryService(EntityManager em) {
		this.em=em;
	}


	public List<Category> findAll() {
		TypedQuery<Category>query=em.createNamedQuery("Category.getAll", Category.class);
		List<Category>list=query.getResultList();
		return list;
	}


	public void save(Category c) {
		em.getTransaction().begin();
		if(c.getId()==0) {//new category
			em.persist(c);
		}
		else {
			em.merge(c);//edit category
		}
		em.getTransaction().commit();
		
	}


	public Category findByid(int id) {
		
		return em.find(Category.class,id);
	}




	


	



	

	
}
