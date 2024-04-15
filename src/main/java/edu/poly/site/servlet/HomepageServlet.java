package edu.poly.site.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Video;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/Homepage")
public class HomepageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			
			VideoDAO dao = new VideoDAO();

			 int currentPage = 1;
	            if (request.getParameter("page") != null) {
	                currentPage = Integer.parseInt(request.getParameter("page"));
	            }
			
			List<Video> list = dao.findAll(currentPage);
			request.setAttribute("videos", list);
			
			int noOfPages = dao.getNoOfPages();
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", currentPage);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
