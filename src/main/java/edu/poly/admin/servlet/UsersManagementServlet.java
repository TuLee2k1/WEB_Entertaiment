package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.UserDAO;
import edu.poly.dao.VideoDAO;
import edu.poly.model.User;
import edu.poly.model.Video;


/**
 * Servlet implementation class UsersManagementServlet
 */
@WebServlet({"/Admin/UsersManagement","/Admin/UsersManagement/edit","/Admin/UsersManagement/create",
		 "/Admin/UsersManagement/update","/Admin/UsersManagement/delete","/Admin/UsersManagement/reset"})
public class UsersManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
		String url = request.getRequestURI().toString();
		if(url.contains("edit")) {
			edit(request,response);
			return;
		}
		
		if(url.contains("delete")) {
			delete(request,response);
			return;
		}
		
		if(url.contains("reset")) {
			reset(request,response);
			return;
		}
		try {
			UserDAO dao = new UserDAO();
			
			List<User> list = dao.findAll();
			request.setAttribute("users", list);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI().toString();
		if(url.contains("create")) {
			create(request,response);
			return;
		}

		if(url.contains("delete")) {
			delete(request,response);
			return;
		}
		
		if(url.contains("update")) {
			update(request,response);
			return;
		}
	
		if(url.contains("reset")) {
			reset(request,response);
			return;
		}
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setAdmin(true);
			 UserDAO dao = new UserDAO();
			 dao.insert(user);
			 
			 request.setAttribute("user", user);
			 
			 request.setAttribute("message", "User is inserted!!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error","Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	   try {
		   if (username != null && !username.isEmpty()) {
		        UserDAO dao = new UserDAO();
		        User user = dao.findById(username);
		        if (user != null) {
		            dao.delete(username);
		            request.setAttribute("message", "User is deleted!!!");
		            request.setAttribute("user", user);
		        } else {
		            request.setAttribute("error", "Error: User not found");
		        }
		    } else {
		        request.setAttribute("error", "Error: Invalid username");
		    }
	} catch (Exception e) {
		// TODO: handle exception
		request.setAttribute("error", e.getMessage());
	}
	    try {
			UserDAO dao = new UserDAO();
			
			List<User> list = dao.findAll();
			request.setAttribute("users", list);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	    PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    if (username != null && !username.isEmpty()) {
	        User user = new User();
	        try {
	            BeanUtils.populate(user, request.getParameterMap());
	            user.setUsername(username);
	            UserDAO dao = new UserDAO();
	            dao.update(user);
	            request.setAttribute("user", user);
	            request.setAttribute("message", "User is updated!!!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Error: " + e.getMessage());
	        }
	    } else {
	        request.setAttribute("error", "Error: Invalid username");
	    }
	    PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setAttribute("user", new User());
	    request.setAttribute("message", "User is reset!!!");
	    PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

}
