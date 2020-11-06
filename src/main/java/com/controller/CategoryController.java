package com.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.controller.service.CategoryService;
import com.mmit.model.entity.*;
@WebServlet({"/categories","/category-add","/category-edit"})

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryService catService;
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	
    	EntityManagerFactory EMF=null;
		Object obj=getServletContext().getAttribute("emf");//application scope
		if(obj==null) {
			EMF=Persistence.createEntityManagerFactory("jpa-crud");
			getServletContext().setAttribute("emf", EMF);
		}else {
			EMF=(EntityManagerFactory) obj;
		}
    	catService=new CategoryService(EMF.createEntityManager());
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
    	if("/category-add".equals(action)) {
    		//get data from req
    		String name=req.getParameter("categoryname");
    		String id=req.getParameter("categoryid");
    		//create entity obj
    		Category c=(id==null ? new Category() : catService.findByid(Integer.parseInt(id)));
    		c.setName(name);
    		//insert into db;
    		catService.save(c);
    		//redirect page
    		resp.sendRedirect(req.getContextPath().concat("/categories"));
    		
    	}
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getServletPath();
		if("/categories".equals(action))
		{	
			//get data from db
			List<Category>list=catService.findAll();
			//set data to req
			request.setAttribute("categories", list);
			//forward show page
			getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);
		}
		else if("/categories-edit".equals(action)) {
			//get id from req
			String catId=request.getParameter("categoryid");
			//get data from db
			Category cat=catService.findByid(Integer.parseInt(catId));
			//set data to req
			request.setAttribute("category", cat);
			//show page
			getServletContext().getRequestDispatcher("/category-edit.jsp").forward(request, response);
		}
	}

	

}
