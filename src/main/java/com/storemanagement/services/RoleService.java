package com.storemanagement.services;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import com.storemanagement.entities.Privilege;
import com.storemanagement.entities.Role;
import com.storemanagement.entities.User;
public class RoleService extends EntityService {
	public static List<Privilege> getPrivileges(Role role) {
		List<Privilege> privileges = null;
		try {
			openSession();
			privileges = getSession().createCriteria(Privilege.class).add(Restrictions.eq("role.id", role.getId())).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return privileges;
	}
	public static boolean hasUsers(Role role){
		List<Privilege> users = null;
		try {
			openSession();
			users = getSession().createCriteria(User.class).add(Restrictions.eq("role.id", role.getId())).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return users.size() > 0;
	}
	public static boolean deletePrivileges(Role role){
		int deleted = 0;
		try{
			openSession();
			deleted = getSession().createQuery("delete from Privilege where role.id = :roleId").setParameter("roleId", role.getId()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return deleted > 0;
	}
}
