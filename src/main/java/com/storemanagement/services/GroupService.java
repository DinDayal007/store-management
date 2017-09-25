package com.storemanagement.services;
import java.util.List;
import com.storemanagement.entities.MainGroup;
import com.storemanagement.entities.SubGroup;
public class GroupService extends EntityService {
	public static List<SubGroup> getSubGroupsFromMainGroup(MainGroup mainGroup) {
		List<SubGroup> subGroups = null;
		try {
			openSession();
			subGroups = getSession()
					.createQuery(
							"from SubGroup where mainGroup_id = :mainGroupId")
					.setParameter("mainGroupId", mainGroup.getId()).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return subGroups;
	}
}
