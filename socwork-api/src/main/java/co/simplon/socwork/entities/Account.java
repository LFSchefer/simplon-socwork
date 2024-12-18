package co.simplon.socwork.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_accounts")
public class Account extends AbstractEntity {

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany
	@JoinTable(
			  name = "t_accounts_roles", 
			  joinColumns = @JoinColumn(name = "account_id"), 
			  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	public Account() {
	}

	public Account(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return Collections.unmodifiableSet(roles);
	}
	
	public void addRole(Role role) {
		roles.add(role);
	}
	
	public void addRoles(Set<Role> roles) {
		roles.addAll(roles);
	}
	
	public void clearRoles() {
		roles.clear();
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password= PROTECTED " + ", roles=" + roles + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return obj instanceof Account other 
				&& this.userName.equals(other.userName); 
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(userName);
	}
}
