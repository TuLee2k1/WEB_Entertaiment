package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDAO;
import edu.poly.model.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfile")
public class EditProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = SessionUtils.getLoginedUsername(request);
		if(username==null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}
		
		try {
			UserDAO dao = new UserDAO();
			User user =  dao.findById(username);
			request.setAttribute("user", user);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			String username = SessionUtils.getLoginedUsername(request);
			UserDAO dao = new UserDAO();
			User oldUser =  dao.findById(username);
			
			user.setAdmin(oldUser.getAdmin());
			dao.update(user);
			
			request.setAttribute("message", "User profile upadated!!!");
			request.setAttribute("user", user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_EDIT_PROFILE_PAGE);
	}
	
}
