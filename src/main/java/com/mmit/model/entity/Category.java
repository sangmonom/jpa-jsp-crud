package com.mmit.model.entity;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@NamedQuery(name="Category.getAll",query="SELECT c FROM Category c")
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cat_id")
	private int id;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category() {
		super();
	}
   
}
