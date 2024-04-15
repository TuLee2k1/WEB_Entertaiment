package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.VideoDAO;
import edu.poly.domain.favoriteUserReport;
import edu.poly.model.Video;

/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/ReportsManagement")
public class ReportsManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		reportFavoritesByVideos(request, response);
		reportFavoriteUsersByVideo(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void reportFavoriteUsersByVideo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try {
			String videoUserId = request.getParameter("videoUserId");
			
			VideoDAO vdao = new VideoDAO();
			List<Video> vlist = vdao.findAll();
			
			if(videoUserId ==null && vlist.size()>0) {
				videoUserId = vlist.get(0).getVideoId();
			}
			
			FavoriteDao dao = new FavoriteDao();
			List<favoriteUserReport> list = dao.reportFavoriteUsersByVideo(videoUserId);
			
			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vlist);
			request.setAttribute("favUsers", list);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: "+e.getMessage());
		}
	}

}
