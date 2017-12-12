package com.storemanagement.services;

import java.util.Date;
import com.storemanagement.entities.User;

public class TransferService extends EntityService {
	public static int confirmTransfer(int transferId, User user){
		try{
			openSession();
			String query = "update TransferHeader set status = 1 , confirmedBy = :user, confirmationDate = :date where id = :id";
			int updated = getSession().createQuery(query).setParameter("user", user).setParameter("date", new Date()).setParameter("id", transferId).executeUpdate();
			return updated;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSession();
		}
		return 0;
	}
}
