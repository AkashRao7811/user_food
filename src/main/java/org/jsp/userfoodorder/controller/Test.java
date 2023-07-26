package org.jsp.userfoodorder.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.jsp.userfoodorder.Config.UserFoodConfig;
import org.jsp.userfoodorder.dao.FoodOrderDao;
import org.jsp.userfoodorder.dao.UserDao;
import org.jsp.userfoodorder.dto.FoodOrder;
import org.jsp.userfoodorder.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class Test {

	@Autowired
	EntityManager manager;
	
	static Scanner sc=new Scanner(System.in);
	
	static UserDao udao;
	static FoodOrderDao fdao;
	
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(UserFoodConfig.class);
		udao=context.getBean(UserDao.class);
	}
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(UserFoodConfig.class);
		fdao=context.getBean(FoodOrderDao.class);
	}
	
	public static void main(String[] args) {
		
		System.out.println("1:save user");
		System.out.println("2:update User");
		System.out.println("3:find user by id");
		System.out.println("4:verify user by email and phone");
		System.out.println("5:delete user");
		System.out.println("6:save foodorder");
		System.out.println("7:update foodorder");
		System.out.println("8:verify foodorder by id and name");
		System.out.println("9:delete foodorder");
		
		int choice=sc.nextInt();
		switch(choice) {
		case 1:{
			saveUser();
			break;
		}
		case 2:{
			updateUser();
			break;
		}
		case 3:{
			findUserById();
			break;
		}
		case 4:{
			verifyUserByEmailAndPhone();
			break;
		}
		case 5:{
			deleteUser();
			break;
		}
		case 6:{
			saveFoodorder();
			break;
		}
		case 7:{
			updateFoodorder();
			break;
		}
		case 8:{
			verifyFoodOrderByIdAndName();
			break;
		}
		case 9:{
			deleteFoodOrder();
			break;
			
		}
		}
	}

	public static void deleteFoodOrder() {
		System.out.println("enter user id to delete");
		int uid=sc.nextInt();
		System.out.println("enter food id to delete");
		int fid=sc.nextInt();
		fdao.deleteFoodOrder(uid, fid);
		
	}

	public static void verifyFoodOrderByIdAndName() {
		System.out.println("enter id and name");
		int id=sc.nextInt();
		String name=sc.next();
		List<FoodOrder> list=fdao.verifyFoodOrderByIdAndName(id, name);
		if(list.size()>0)
			for(FoodOrder f:list) {
				System.out.println(f);
			}
		
	}

	public static void saveFoodorder() {
		System.out.println("enter id to find");
		int id=sc.nextInt();
		System.out.println("enter name to save food");
		String name=sc.next();
		FoodOrder f=new FoodOrder();
		f.setName(name);
		FoodOrder fo=fdao.saveFoodOrder(f, id);
		if(fo!=null)
		System.out.println("foodorder saved with id:"+f.getId());
		else
			System.out.println("foodorder not saved");
		
	}
	

	public static void updateFoodorder() {
		System.out.println("enter id to find");
		int id=sc.nextInt();
		User u=udao.findUserById(id);
		if(u!=null) {
		System.out.println("enter id,name to save food");
		int fid=sc.nextInt();
		String name=sc.next();
		FoodOrder f=new FoodOrder();
		f.setId(fid);
		f.setName(name);
		FoodOrder fo=fdao.updateFoodOrder(f, id);
		if(fo!=null)
		System.out.println("foodorder updated with id:"+f.getId()+"with user id:"+u.getId());
		else
			System.out.println("foodorder not updated");
		}
		
	}

	public static void deleteUser() {
		System.out.println("enter id to delete");
		int id=sc.nextInt();
		udao.deleteUser(id);
		
	}

	public static void verifyUserByEmailAndPhone() {
		System.out.println("enter email and phone to verify user");
		String email=sc.next();
		long phone=sc.nextLong();
		User u=new User();
		u=udao.verifyByEmailAndPhone(email, phone);
		System.out.println(u);
		
	}

	public  static void findUserById() {
		System.out.println("enter id to find");
		int id=sc.nextInt();
		User u=new User();
		u=udao.findUserById(id);
		System.out.println(u);
		
	}

	public static void saveUser() {
		System.out.println("enter name,age,email,phone to save the user ");
		String name=sc.next();
		int age=sc.nextInt();
		String email=sc.next();
		long phone=sc.nextLong();
		User u=new User();
		u.setName(name);
		u.setAge(age);
		u.setEmail(email);
		u.setPhone(phone);
		u=udao.saveUser(u);
		System.out.println("user saved with id:"+u.getId());
		
	}
	
	public static void updateUser() {
		System.out.println("enter id,name,age,email,phone to save the user ");
		int id=sc.nextInt();
		String name=sc.next();
		int age=sc.nextInt();
		String email=sc.next();
		long phone=sc.nextLong();
		User u=new User();
		u.setId(id);
		u.setName(name);
		u.setAge(age);
		u.setEmail(email);
		u.setPhone(phone);
		u=udao.updateUser(u);
		System.out.println("user saved with id:"+u.getId());
		
	}
}
