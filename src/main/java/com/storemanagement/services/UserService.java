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
	public User login() {
		return null;
	}
	//block user or unblock user
	public boolean blockOrUnblock() {
		return false;
	}
}
