package edu.poly.dao;


import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.model.Video;

public class VideoDAO extends AbstractEntityDao<Video> {

	public VideoDAO() {
		super(Video.class);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Video findByVideoID(String videoId) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select u from Video u where u.id = :videoId";
		try {
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("videoId", videoId);

			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
	
	public List<Video> findAll(int currentPage) {
	    int startIndex = (currentPage - 1) * PAGE_SIZE;
	    int endIndex = Math.min(startIndex + PAGE_SIZE, findAll().size());
	    if (endIndex - startIndex < PAGE_SIZE && currentPage != 1) {
	        endIndex = findAll().size();
	    }
	    if (startIndex >= endIndex) {
	        return Collections.emptyList();
	    }
	    return findAll().subList(startIndex, endIndex);
	}
	
	public int getNoOfPages() {
	    int noOfRecords = findAll().size();
	    int noOfPages = (int) Math.ceil(noOfRecords / (double) PAGE_SIZE);
//	    if (noOfRecords % PAGE_SIZE != 0) {
//	        noOfPages++;
//	    }
	    return noOfPages;
	}
	public static final int PAGE_SIZE = 6;
	
}
