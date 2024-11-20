package co.simplon.socwork.services;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountSignIn;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.mappers.AccountMapper;
import co.simplon.socwork.repositories.AccountRepository;

@Service
@Transactional(readOnly = true)
public class AccountService {
	
	private final AccountRepository repo;
		
	private final PasswordEncoder encoder;

	public AccountService(AccountRepository repo, PasswordEncoder encoder) {
		this.repo = repo;
		this.encoder = encoder;
	}

	@Transactional
	public void create(AccountCreate inputs) {
		String hashPassword = encoder.encode(inputs.password());
		repo.save(AccountMapper.toEntity(inputs, hashPassword));
	}

	@Transactional
	public String signIn(AccountSignIn inputs) {
		Account account = repo.findByUserNameIgnoreCase(inputs.userName().trim())
				.orElseThrow( () -> new BadCredentialsException(inputs.userName()));
		boolean match = encoder.matches(inputs.password(), account.getPassword());	
		if (match) {
			return String.format("Success sign in with account %s ", inputs.userName());
		} else {
			return String.format("Wrong password with account %s ", inputs.userName());
		}
	}

	
}
