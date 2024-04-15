package edu.poly.site.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Favorite;
import edu.poly.model.Video;

/**
 * Servlet implementation class FavoriteServlet
 */
@WebServlet("/Favorite")
public class FavoriteServlet extends HttpServlet {

       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	if(!SessionUtils.isLogin(request)) {
				response.sendRedirect("Login");
				return;
			}
	    	String username = SessionUtils.getLoginedUsername(request);
	        FavoriteDao dao = new FavoriteDao();
	        List<Favorite> favoriteList = dao.findFavoriteByUser(username); // find favorite entities by username
	        System.out.println(favoriteList.size());
	        System.out.println(username);
	        List<Video> videoList = new ArrayList<>();
	        for (Favorite favorite : favoriteList) {
	            videoList.add(favorite.getVideo());
	        }

	        request.setAttribute("videos", videoList);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", e.getMessage());
	    }
	    PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FAVORITE_PAGE);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
