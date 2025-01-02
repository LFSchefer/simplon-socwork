package co.simplon.socwork.mappers;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AuthInfo;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.entities.Role;

import java.util.Set;

public final class AccountMapper {

	public static Account toEntity(AccountCreate inputs, String hashPassword, Role role) {
		Account account = new Account(inputs.userName().trim(),hashPassword);
		account.addRole(role);
		return account;
	}

    public static AuthInfo toAuthInfo(String token, Set<Role> roles) {
		return new AuthInfo(token, roles.stream().map(r -> r.getName()).toList());
    }
}
