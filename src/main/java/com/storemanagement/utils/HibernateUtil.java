package com.storemanagement.utils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Failed to build the factory");
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
