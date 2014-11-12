package net.franckbenault.jpa.hibernate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Student {
  @Id
  private long id = System.currentTimeMillis();

  //the annotation basic is not needed for Hibernate and EclipseLink
  //@Basic
  private String name;

  //hibernate could accept @Basic here
  //@Basic
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)  
  private Date dateOfBirth = new Date();

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
}

