package com.storemanagement.utils;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.Role;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.GroupService;
public class Application {
	public static void main(String[] args) {
		
//		Role role = new Role();
//		role.setName("admin");
//		EntityService.addObject(role);
//		Role role2 = new Role();
//		role2.setName("normal");
//		EntityService.addObject(role2);
//		
//		User user = new User();
//		user.setName("mohammed yehia");
//		user.setPassword("123456");
//		user.setRole(role);
//		user.setStatus(1);
//		User user2 = new User();
//		user2.setName("ihab mostafa");
//		user2.setPassword("0000");
//		user2.setRole(role2);
//		user2.setStatus(1);
//		EntityService.addObject(user);
//		EntityService.addObject(user2);
		
//		Inventory inv = new Inventory();
//		inv.setName("second inventory");
//		inv.setDescription("second inventory description");
//		EntityService.addObject(inv);
		
//		Supplier sup = new Supplier();
//		sup.setCode("123465");
//		sup.setDescription("some description");
//		sup.setEmail("email");
//		sup.setMobile1("0155455445");
//		sup.setMobile2("0144586148");
//		sup.setName("mohammed");
//		sup.setPhone("0485632");
//		EntityService.addObject(sup);
//		Unit unit = new Unit();
//		unit.setName("unit name 2");
//		unit.setDescription("unit description 2");
//		EntityService.addObject(unit);
		
//		MainGroup mg = new MainGroup();
//		mg.setName("main group");
//		EntityService.addObject(mg);
//		
//		SubGroup sg = new SubGroup();
//		sg.setName("sub group");
//		sg.setMainGroup(mg);
//		service.addObject(sg);
//		
//		Item item = new Item();
//		item.setCode("123456");
//		item.setName("new item");
//		item.setPrice(500.0);
//		item.setExpirationDate(new Date());
//		item.setProductionDate(new Date());
//		item.setHome("egypt");
//		item.setMinLimit(5);
//		item.setMaxLimit(100);
//		item.setTax(5.5);
//		item.setSubGroup(sg);
//		item.setSupplier(sup);
//		item.setUnit(unit);
//		service.addObject(item);
	}
}
