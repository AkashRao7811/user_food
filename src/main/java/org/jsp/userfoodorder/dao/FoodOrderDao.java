package org.jsp.userfoodorder.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.userfoodorder.dto.FoodOrder;
import org.jsp.userfoodorder.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodOrderDao {

	@Autowired
	EntityManager manager;
	
	public FoodOrder saveFoodOrder(FoodOrder f,int u_id) {
		User u=manager.find(User.class, u_id);
		if(u!=null) {
			u.getOrders().add(f);
			f.setUsers(u);
			manager.persist(f);
			EntityTransaction transaction=manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return f;
		}
		return null;
	}
	
	public FoodOrder updateFoodOrder(FoodOrder f,int u_id) {
		User u=manager.find(User.class, u_id);
		if(u!=null) {
			u.getOrders().add(f);
			f.setUsers(u);
			manager.merge(f);
			EntityTransaction transaction=manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return f;
		}
		return null;
	}
	
	public List<FoodOrder> verifyFoodOrderByIdAndName(int id,String name) {
		Query q=manager.createQuery("select u.orders from User u where u.id=?1 and u.name=?2 ");
		q.setParameter(1, id);
		q.setParameter(2, name);
		return q.getResultList();
		
	}
	
	public void deleteFoodOrder(int u_id,int f_id) {
		User u=manager.find(User.class, u_id);
		if(u!=null) {
			FoodOrder f=manager.find(FoodOrder.class, f_id);
			if(f!=null) {
				f.setUsers(null);
				manager.remove(f);
				EntityTransaction transaction=manager.getTransaction();
				transaction.begin();
				transaction.commit();
				
			}
		}
	}
}
