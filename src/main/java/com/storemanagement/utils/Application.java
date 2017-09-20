package com.storemanagement.utils;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
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
		EntityService service = new EntityService();
		
		Supplier sup = new Supplier();
		sup.setCode("123465");
		sup.setDescription("some description");
		sup.setEmail("email");
		sup.setMobile1("0155455445");
		sup.setMobile2("0144586148");
		sup.setName("mohammed");
		sup.setPhone("0485632");
		service.addObject(sup);
		Unit unit = new Unit();
		unit.setName("unit name");
		unit.setDescription("unit description");
		service.addObject(unit);
		
		MainGroup mg = new MainGroup();
		mg.setName("main group");
		service.addObject(mg);
		
		SubGroup sg = new SubGroup();
		sg.setName("sub group");
		sg.setMainGroup(mg);
		service.addObject(sg);
		
		Item item = new Item();
		item.setCode("123456");
		item.setName("new item");
		item.setPrice(500.0);
		item.setExpirationDate(new Date());
		item.setProductionDate(new Date());
		item.setHome("egypt");
		item.setMinLimit(5);
		item.setMaxLimit(100);
		item.setTax(5.5);
		item.setSubGroup(sg);
		item.setSupplier(sup);
		item.setUnit(unit);
		service.addObject(item);
	}
}
