package com.storemanagement.utils;
import org.hibernate.Session;
public class HibernateDriver {
	private static Session session;
	public static void openSession() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
	}
	public static void closeSession() {
		session.getTransaction().commit();
		session.close();
	}
	public static Session getSession() {
		return session;
	}
}
