package com.storemanagement.services;
import java.util.List;
import com.storemanagement.entities.Branch;
import com.storemanagement.entities.User;
public class UserService extends EntityService {
	//get users from a branch
	public static boolean hasUsersFromBranch(Branch branch) {
		List<User> users = null;
		try {
			openSession();
			users = getSession()
					.createQuery(
							"from User where branch.id = :branchId")
					.setParameter("branchId", branch.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return users.size() > 0;
	}
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
