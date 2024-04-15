package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDao;
import edu.poly.model.Favorite;
import edu.poly.model.Video;

/**
 * Servlet implementation class UnLikeServlet
 */
@WebServlet("/UnLikeVideo")
public class UnLikeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		String username = SessionUtils.getLoginedUsername(request);
		String page = request.getParameter("page");
		String videoId = request.getParameter("videoId");
		
		if(videoId == null) {
			response.sendRedirect("Homepage");
			return;
		}
		
		try {
		    FavoriteDao dao = new FavoriteDao();
		    
		    Favorite favorite = dao.findFavoriteByVideoId(videoId,username);
		    if (favorite != null) {
		    	System.out.println(favorite.getFavoriteId());
		        dao.delete(favorite.getFavoriteId());
		        request.setAttribute("message", "Video is unlike to MyFavorite");
		    } else {
		        request.setAttribute("message", "Video is not in Favorite");
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    request.setAttribute("error", e.getMessage());
		}
		
		if(page == null) {
			page="/Homepage";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
