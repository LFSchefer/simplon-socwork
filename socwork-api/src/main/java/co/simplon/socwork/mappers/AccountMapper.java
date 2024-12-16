package co.simplon.socwork.mappers;

import java.util.Set;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.entities.Role;

public final class AccountMapper {

	public static Account toEntity(AccountCreate inputs, String hashPassword, Role role) {
		Account account = new Account();
		account.setUserName(inputs.userName().trim());
		account.setPassword(hashPassword);
		account.setRoles(Set.of(role));
		return account;
	}

}
