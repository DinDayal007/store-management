package com.storemanagement.services;
import java.util.List;
import com.storemanagement.utils.HibernateDriver;
class EntityService extends HibernateDriver {
	public EntityService() {}
	/**
	 * Generic method to add new entity
	 * 
	 * @param object
	 * @return
	 */
	public <T extends Object> int addObject(T object) {
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
	public <T extends Object> Object getObject(Class<T> cls, int objectId) {
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
	public <T extends Object> void updateObject(T object) {
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
	public <T extends Object> void removeObject(T object) {
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
	 * Generic method to return list of entities
	 * 
	 * @param cls
	 * @return
	 */
	public <T extends Object> List<T> getAllObjects(Class<T> cls) {
		List<T> objects = null;
		try {
			openSession();
			objects = getSession().createQuery("from " + cls.getName()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return objects;
	}
}
