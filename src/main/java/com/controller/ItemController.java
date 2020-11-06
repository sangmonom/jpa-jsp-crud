package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mmit.model.entity.*;

import com.mmit.controller.service.CategoryService;
import com.mmit.controller.service.ItemService;
@WebServlet({"item-add","/items","/item-delete"})
public class ItemController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private CategoryService catService;
	private ItemService itemservice;
	@Override
	public void init() throws ServletException {
		
		super.init();
		EntityManagerFactory EMF=null;
		Object obj=getServletContext().getAttribute("emf");//application scope
		if(obj==null) {
			EMF=Persistence.createEntityManagerFactory("jpa-crud");
			getServletContext().setAttribute("emf", EMF);
		}else {
			EMF=(EntityManagerFactory) obj;
		}
    	catService=new CategoryService(EMF.createEntityManager());
    	itemservice=new ItemService(EMF.createEntityManager());
    }
    @Override
    public void destroy() {
    	EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
    	if(emf!=null && emf.isOpen())
    	emf.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String action=req.getServletPath();
    	if("/item-add".equals(action)) {
    		//get data from db
    		String itemid=req.getParameter("itemid");
    		String catId=req.getParameter("categoryid");
    		String name=req.getParameter("itemname");
    		String price=req.getParameter("price");
    		//create item obj
    		Item item=(itemid==null || itemid.equals("") ? new Item() : itemservice.findByid(Integer.parseInt(itemid)));
    		item.setName(name);
    		item.setPrice(Integer.parseInt(price));
    		item.setCategory(catService.findByid(Integer.parseInt(catId)));
    		//insert to db
    		itemservice.save(item);
    		//redirect page
    		resp.sendRedirect(req.getContextPath().concat("/items"));
    	}
    }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		if("/item-add".equals(path) || "/item-edit".equals(path)) {
			//get category list from db
			List<Category> catlist=catService.findAll();
			//add list to req
			req.setAttribute("categories", catlist);
			if("/item-edit".equals(path)) {
				//get id from req
				String id=req.getParameter("itemid");
				//get data from db
				Item itemObj=itemservice.findByid(Integer.parseInt(id));
				//set data to req
				req.setAttribute("items", itemObj);
				//show page
				getServletContext().getRequestDispatcher("/category-add.jsp").forward(req, resp);
			}
			//forward page
			getServletContext().getRequestDispatcher("/item-add.jsp").forward(req, resp);
		}
		
		else if("/items".equals(path)) {
				//get item from db
				List<Item> list=itemservice.findAll();		
				//add item to req
				req.setAttribute("items", list);
				//show page
				getServletContext().getRequestDispatcher("/item.jsp").forward(req, resp);
			}
		else if("/item-delete".equals(path))
		{
			//get id form req
			String itemid=req.getParameter("itemid");
			//remove from db
			itemservice.delete(Integer.parseInt(itemid));
			//redirect page
			resp.sendRedirect(req.getContextPath().concat("/items"));
		}
		
		}
	
	}

