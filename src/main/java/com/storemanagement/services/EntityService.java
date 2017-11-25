package com.storemanagement.services;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.SalesInvoiceDetails;
import com.storemanagement.utils.HibernateDriver;
public class EntityService extends HibernateDriver {
	public EntityService() {}
	/**
	 * Generic method to add new entity
	 * 
	 * @param object
	 * @return
	 */
	public static <T extends Object> int addObject(T object) {
		int objId = 0;
		try {
			openSession();
			objId = (Integer) getSession().save(object);
			System.out.println("Object saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return objId;
	}
	/**
	 * Generic method to get an entity by its id
	 * 
	 * @param objectId
	 * @param cls
	 * @return
	 */
	public static <T extends Object> Object getObject(Class<T> cls, int objectId) {
		Object obj = null;
		try {
			openSession();
			obj = getSession().get(cls, objectId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return obj;
	}
	/**
	 * Generic method to update an entity
	 * 
	 * @param object
	 * @return
	 */
	public static <T extends Object> void updateObject(T object) {
		try {
			openSession();
			getSession().update(object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}
	/**
	 * Generic method to remove an entity
	 * 
	 * @param object
	 * @return
	 */
	public static <T extends Object> void removeObject(T object) {
		try {
			openSession();
			getSession().delete(object);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
	}
	/**
	 * Generic method to return list of entities order by id desc
	 * 
	 * @param cls
	 * @return
	 */
	public static <T extends Object> List<T> getAllObjects(Class<T> cls) {
		List<T> objects = null;
		try {
			openSession();
			objects = getSession().createCriteria(cls.getName()).addOrder(Order.desc("id")).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return objects;
	}
	/**
	 * Generic method to return list of entities order by column with order as an argument
	 * 
	 * @param cls
	 * @return
	 */
	public static <T extends Object> List<T> getObjectsWithOrder(Class<T> cls, String column, String order) {
		List<T> objects = null;
		try {
			openSession();
			if(order.equals("desc"))
				objects = getSession().createCriteria(cls.getName()).addOrder(Order.desc(column)).list();
			else if(order.equals("asc"))
				objects = getSession().createCriteria(cls.getName()).addOrder(Order.asc(column)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return objects;
	}
	
	/**
	 * Generic method to return list of entities with restriction of column eq value
	 * @param cls
	 * @param column
	 * @param value
	 * @return
	 */
	public static <T extends Object> List<T> getObjectsWithEqRestriction(Class<T> cls, String column, Object value){
		List<T> objects = null;
		try {
			openSession();
			objects = getSession().createCriteria(cls.getName()).add(Restrictions.eq(column, value)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return objects;
	}
}
