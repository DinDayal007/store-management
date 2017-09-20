package com.storemanagement.utils;
import java.util.List;
import com.storemanagement.entities.User;
import com.storemanagement.services.UserService;
public class Application {
	public static void main(String[] args) {
		UserService service = new UserService();
		List<User> users = service.getAllObjects(User.class);
		System.out.println("number of users => " + users.size());
		for(User user : users)
			System.out.println(user.getName() + " => " + user.getRole().getName());
	}
}
