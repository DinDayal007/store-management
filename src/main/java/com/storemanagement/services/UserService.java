package com.storemanagement.services;
import com.storemanagement.entities.User;
public class UserService extends EntityService {
	//login using name and password
	public static User login(String name, String password) {
		User user = null;
		try {
			openSession();
			user = (User) getSession().createQuery("from User where name = :name and password = :password").setParameter("name", name).setParameter("password", password).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return user;
	}
	//block user or unblock user
	public boolean blockOrUnblock() {
		return false;
	}
}
