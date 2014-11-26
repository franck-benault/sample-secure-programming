package net.franckbenault.securecoding.sqlinjection.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="personjpa")
@Entity
public class Person {
	
	@Id
	private long id = System.currentTimeMillis();
	
	private String firstname;
	
	private String lastname;
	
	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.id = System.currentTimeMillis();
		this.setFirstname(firstName);
		this.setLastname(lastName);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


}
