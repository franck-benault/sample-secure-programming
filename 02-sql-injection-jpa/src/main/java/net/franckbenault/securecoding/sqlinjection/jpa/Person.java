package net.franckbenault.securecoding.sqlinjection.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	
	@Id
	private long id = System.currentTimeMillis();
	
	private String firstName;
	
	private String lastName;
	
	public Person() {
	}

	public Person(String firstName, String lastName) {
		this.id = System.currentTimeMillis();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
