package model.user;

import java.util.Date;
import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userID;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private String email;
	private String hash;
	public User(int userID, String name, String surname, Date dateOfBirth, String email, String hash) {
		super();
		this.userID = userID;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.hash = hash;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
