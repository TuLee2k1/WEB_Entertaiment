package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Video;

/**
 * Servlet implementation class VideosManagementServlet
 */
@WebServlet({"/Admin/VideosManagement","/Admin/VideosManagement/edit","/Admin/VideosManagement/create",
	"/Admin/VideosManagement/update","/Admin/VideosManagement/delete","/Admin/VideosManagement/reset"})
@MultipartConfig
public class VideosManagementServlet extends HttpServlet {

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
		Video video = new Video();
		video.setPoster("iamges/mac6.png");
		request.setAttribute("video", video);
		
		try {
			VideoDAO dao = new VideoDAO();
			
			List<Video> list = dao.findAll();
			request.setAttribute("videos", list);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
		
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
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
		Video video = new Video();
		
		try {
			BeanUtils.populate(video, request.getParameterMap());
			
			video.setPoster("images/"+ UploadUtils.processUploadField("vover",request,"/images",video.getVideoId()));
			 VideoDAO dao = new VideoDAO();
			 dao.insert(video);
			 
			 request.setAttribute("video", video);
			 
			 request.setAttribute("message", "Video is inserted!!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error","Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String videoId = request.getParameter("videoId");
	    try {
	    	if (videoId != null && !videoId.isEmpty()) {
		        VideoDAO dao = new VideoDAO();
		        Video video = dao.findById(videoId);
		        if (video != null) {
		            dao.delete(videoId);
		            request.setAttribute("message", "Video is deleted!!!");
		            request.setAttribute("video", video);
		        } else {
		            request.setAttribute("error", "Error: Video not found");
		        }
		    } else {
		        request.setAttribute("error", "Error: Invalid video ID");
		    }
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", e.getMessage());
		}
	    try {
			VideoDAO dao = new VideoDAO();
			
			List<Video> list = dao.findAll();
			request.setAttribute("videos", list);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	    PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String videoId = request.getParameter("videoId");
	    if (videoId != null && !videoId.isEmpty()) {
	        Video video = new Video();
	        try {
	            BeanUtils.populate(video, request.getParameterMap());
	            video.setVideoId(videoId);
	            video.setPoster("images/" + UploadUtils.processUploadField("vover", request, "/images", videoId));
	            VideoDAO dao = new VideoDAO();
	            dao.update(video);
	            request.setAttribute("video", video);
	            request.setAttribute("message", "Video is updated!!!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Error: " + e.getMessage());
	        }
	    } else {
	        request.setAttribute("error", "Error: Invalid video ID");
	    }
	    PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}
	
	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setAttribute("video", new Video());
	    request.setAttribute("message", "Video is reset!!!");
	    PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

}
