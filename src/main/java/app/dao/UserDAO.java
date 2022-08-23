package app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.models.User;

public class UserDAO {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	static {
		sessionFactory = new Configuration()
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
	}
	
	public static List<User> getAllUsers(){
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<User> users = session.createNativeQuery("SELECT * FROM users", User.class).getResultList();
		session.getTransaction().commit();
		return users;
	}
	
	public static User getUser (int id) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = session.get(User.class, id);
		session.getTransaction().commit();
		return user;
	}
	
	public static void createUser(User user) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public static void updateUser(int id, String name, String surname) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = session.get(User.class, id);
		user.setName(name);
		user.setSurname(surname);
		session.getTransaction().commit();
	}
	
	public static void deleteUser(User user) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
	}
}
