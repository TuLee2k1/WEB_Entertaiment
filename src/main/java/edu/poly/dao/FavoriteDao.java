package edu.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.domain.FavoriteReport;
import edu.poly.domain.favoriteUserReport;
import edu.poly.model.Favorite;

public class FavoriteDao extends AbstractEntityDao<Favorite> {

	public FavoriteDao() {
		super(Favorite.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<favoriteUserReport> reportFavoriteUsersByVideo(String videoId){
		String jpql = "select new edu.poly.domain.favoriteUserReport(f.user.username,f.user.fullname, "+"f.user.email, f.likeDate) from Favorite f where f.video.videoId = :videoId";
		 
		EntityManager em = JpaUtils.getEntityManager();
		
		List<favoriteUserReport> list = null;
		
		try {
			TypedQuery<favoriteUserReport> query = em.createQuery(jpql, favoriteUserReport.class);
			
			query.setParameter("videoId",videoId);
			
			list = query.getResultList();
		} finally {
			// TODO: handle exception
			em.close();
		}
		return list;
		}
	
	public List<Favorite> findFavoriteByUser(String username) {
		EntityManager em = JpaUtils.getEntityManager();
		TypedQuery<Favorite> query = em.createQuery("SELECT f FROM Favorite f WHERE f.user.username = :username", Favorite.class);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
	public Favorite findFavoriteByVideoId(String videoId,String username) {
	    List<Favorite> favorites = findAll(); // Assuming that you have a findAll() method that returns a list of all Favorite objects
	    for (Favorite favorite : favorites) {
	        if (favorite.getVideo().getVideoId().equals(videoId)&& favorite.getUser().getUsername().equals(username)) {
	            return favorite;
	        }
	    }
	    return null; // Return null if no Favorite object is found for the given videoId
	}
	
}
