package com.storemanagement.utils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.storemanagement.entities.Cache;
import com.storemanagement.entities.CacheMovement;
import com.storemanagement.entities.Client;
import com.storemanagement.entities.Inventory;
import com.storemanagement.entities.Item;
import com.storemanagement.entities.ItemBalance;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.Page;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.Role;
import com.storemanagement.entities.SalesInvoiceDetails;
import com.storemanagement.entities.SubGroup;
import com.storemanagement.entities.Supplier;
import com.storemanagement.entities.Unit;
import com.storemanagement.entities.User;
import com.storemanagement.services.EntityService;
import com.storemanagement.services.ItemService;
public class Application {
	public static void main(String[] args) {
					
		String[] names = {"متابعة فواتير البيع", "متابعة فواتير مرتجع البيع", "فاتورة شراء جديدة", "متابعة فواتير الشراء", "متابعة فواتير مرتجع الشراء", "الأصناف", "جرد الأصناف", "مجموعات الأصناف الرئيسية", "مجموعات الأصناف الفرعية", "وحدات الأصناف", "الموردين", "العملاء", "الخزائن", "حركة الخزنة", "كشف حساب حركة الخزنة", "كشف مديونيات العملاء", "تقرير هامش الربح", "الفروع", "المخازن", "المستخدمين", "لوحة التحكم", "التقارير"};
		String[] urls = {"sales/invoices.jsp", "sales/return-invoices.jsp", "purchases", "purchases/invoices.jsp", "purchases/return-invoices.jsp", "items", "item-balance", "groups", "subgroups", "units", "suppliers", "clients", "caches", "cache-movements", "cache-sum", "debits", "profit", "branches", "inventories", "users", "settings", "reports"};
		for(int i = names.length - 1; i >= 0; i--){
			Page page = new Page();
			page.setName(names[i]);
			page.setUrl("/store-management-system/" + urls[i]);
			EntityService.addObject(page);
		}
				
//		int itemBalance = ItemService.getItemBalance(6, 1);
//		System.out.println(itemBalance);
		
//		CacheMovement cacheMovement = new CacheMovement();
//		cacheMovement.setAmount(500);
//		cacheMovement.setCache(null);
//		cacheMovement.setDate(new Date());
//		cacheMovement.setDescription("");
//		cacheMovement.setInventory(null);
//		cacheMovement.setType(0);
//		cacheMovement.setUser(null);
//		EntityService.addObject(cacheMovement);
		
//		Unit unit = new Unit();
//		unit.setName("دستة");
//		unit.setDescription("دستة دستة دستة");
//		unit.setQty(12);
//		unit.setCreatedDate(new Date());
//		EntityService.addObject(unit);
		
//		List<ItemMovement> itemMovements = EntityService.getAllObjects(ItemMovement.class);
//		System.out.println("****************************************");
//		System.out.println("size of list from view is " + itemMovements.size());
//		Cache cache = new Cache();
//		cache.setName("احمد");
//		cache.setQyt(650);
//		EntityService.addObject(cache);
		
//		Client client = new Client();
//		client.setCode("012024");
//		client.setDescription("some description 2");
//		client.setEmail("moh@mail.com");
//		client.setMobile1("0155455445");
//		client.setMobile2("0144586148");
//		client.setName("mahmoud");
//		client.setPhone("048299999");
//		EntityService.addObject(client);
//		
//		Role role = new Role();
//		role.setName("admin");
//		EntityService.addObject(role);
//		Role role2 = new Role();
//		role2.setName("normal");
//		EntityService.addObject(role2);
////		
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
//		
//		Inventory inv = new Inventory();
//		inv.setName("second inventory");
//		inv.setDescription("second inventory description");
//		EntityService.addObject(inv);
//		
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
//		
//		MainGroup mg = new MainGroup();
//		mg.setName("المجموعة الرئيسية الاولى");
//		EntityService.addObject(mg);
//		SubGroup sg = new SubGroup();
//		sg.setName("المجموعة الفرعية الثانية");
//		sg.setMainGroup(mg);
//		EntityService.addObject(sg);
//		SubGroup sg2 = new SubGroup();
//		sg2.setName("المجموعة الفرعية الثالثة");
//		sg2.setMainGroup(mg);
//		EntityService.addObject(sg2);
////		
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
//		EntityService.addObject(item);
	}
}
