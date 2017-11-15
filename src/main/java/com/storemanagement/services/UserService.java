package com.storemanagement.services;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.User;
public class UserService extends EntityService {
	//login using name and password
	public static User login(String name, String password) {
		User user = null;
		try {
			openSession();
			user = (User) getSession().createQuery("from User where name = :name and password = :password and status != 0").setParameter("name", name).setParameter("password", password).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return user;
	}
	//get users that have a status = 1 only
	public static List<User> getUsers(){
		List<User> users = null;
		try {
			openSession();
			users = getSession().createCriteria(User.class).add(Restrictions.ne("status", 2)).addOrder(Order.desc("id")).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return users;
	}
	//block user or unblock user
	public static int blockOrUnblock(int id, int status) {
		int updateQuery = 0;
		try{
			openSession();
			updateQuery = getSession().createQuery("update User set status = :status where id = :id").setParameter("status", status).setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return updateQuery;
	}
}
