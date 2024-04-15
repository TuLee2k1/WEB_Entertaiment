package edu.poly.site.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;

import edu.poly.dao.VideoDAO;

import edu.poly.model.Video;

@WebServlet("/Detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!SessionUtils.isLogin(request)) {
			response.sendRedirect("Login");
			return;
		}
		
		String videoId = request.getParameter("videoId");
		VideoDAO dao = new VideoDAO();
		Video video = dao.findByVideoID(videoId);
		
		if(videoId == null) {
			response.sendRedirect("Homepage");
			return;
		}
		
		request.setAttribute("video", video);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_VIDEO_DETAIL_PAGE);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
