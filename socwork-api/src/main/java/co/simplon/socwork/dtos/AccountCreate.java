package co.simplon.socwork.dtos;

public record AccountCreate(String userName, String password) {

	@Override
	public String toString() {
		return "AccountCreate [userName=" + userName + ", password=[PROTECTED] ]";
	}
	
}
