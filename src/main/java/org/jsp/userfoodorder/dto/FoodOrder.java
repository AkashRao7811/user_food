package org.jsp.userfoodorder.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@CreationTimestamp
	private LocalDateTime arr_time;
	@UpdateTimestamp
	private LocalDateTime del_time;
	
	@ManyToOne
	@JoinColumn
	private User users;

	@Override
	public String toString() {
		return "FoodOrder [id=" + id + ", name=" + name + ", arr_time=" + arr_time + ", del_time=" + del_time + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getArr_time() {
		return arr_time;
	}

	public void setArr_time(LocalDateTime arr_time) {
		this.arr_time = arr_time;
	}

	public LocalDateTime getDel_time() {
		return del_time;
	}

	public void setDel_time(LocalDateTime del_time) {
		this.del_time = del_time;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}
}
