package com.springboot.CRUDdemo.entity;

import javax.persistence.*;

@Entity
@Table(name="authority")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer Id;
	
	@Column(name="authority")
	private String authority;
	
	@Column(name="password")
	private String password;
	
	//mappedBy role in Student class: mappedBy = "role"
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "role")  //role is in the Student Role role object name
//	private Student student;
	
	public Role() {
		
	}

	public Role(String authority, String password) {
		this.authority = authority;
		this.password = password;
	}
	
//	public Student getStudent() {
//		return student;
//	}
//	public void setStudent(Student student) {
//		this.student = student;
//	}

	public Integer getId() {
		return Id;
	}
	public void setId(Integer Id) {
		this.Id = Id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "authority=" + authority + ", password=" + password + "]";
	}
	
	
}
