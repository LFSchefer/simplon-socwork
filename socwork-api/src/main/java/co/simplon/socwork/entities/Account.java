package co.simplon.socwork.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_accounts")
public class Account extends AbstractEntity {

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;

	public Account() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=[PROTECTED]" + "]";
	}
	
	
}
