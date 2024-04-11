package edu.poly.dao;


import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.security.enterprise.credential.Password;

import edu.poly.model.User;

public class UserDAO extends AbstractEntityDao<User> {

	public UserDAO() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}
	
	public void changePassword(String username, String oldPassword, String newPassword) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		String jpql = "select u from User u where u.username =:username and u.password=:password";
		try {
			trans.begin();
//			TypedQuery<User> query = em.createQuery(jpql, User.class);
//			query.setParameter("username", username);
//			query.setParameter("password", oldPassword);
//			User user = query.getSingleResult();
//			if (user == null) {
//				throw new Exception("Current password or username are incorrect!");
//			}
//			user.setPassword(newPassword);
			User user = em.find(User.class, username);
			if (user == null || !user.getPassword().equals(oldPassword)) {
				throw new Exception("Current password or username are incorrect!");
			}
			user.setPassword(newPassword);
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	public User findByUsernameEmail(String username, String email) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "select u from User u where u.id = :username and u.email = :email";
		try {
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("email", email);

			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
}
