package co.simplon.socwork.mappers;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.entities.Account;

public final class AccountMapper {

	public static Account toEntity(AccountCreate inputs, String hashPassword) {
		Account account = new Account();
		account.setUserName(inputs.userName().trim());
		account.setPassword(hashPassword);
		return account;
	}

}
