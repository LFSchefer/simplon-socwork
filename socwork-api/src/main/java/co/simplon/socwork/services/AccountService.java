package co.simplon.socwork.services;


import co.simplon.socwork.dtos.AuthInfo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.socwork.config.JwtProvider;
import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountSignIn;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.entities.Role;
import co.simplon.socwork.mappers.AccountMapper;
import co.simplon.socwork.repositories.AccountRepository;
import co.simplon.socwork.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
public class AccountService {
	
	private final AccountRepository repo;
	private final RoleRepository roleRepo;
	private final PasswordEncoder encoder;
	private final JwtProvider jwt;
		
	public AccountService(AccountRepository repo, RoleRepository roleRepo, PasswordEncoder encoder, JwtProvider jwt) {
		this.repo = repo;
		this.roleRepo = roleRepo;
		this.encoder = encoder;
		this.jwt = jwt;
	}

	@Transactional
	public void create(AccountCreate inputs) {
		String hashPassword = encoder.encode(inputs.password());
		Role defaultRole = roleRepo.getDefaultRole().orElseThrow( () -> new InternalError("No default role find"));
		repo.save(AccountMapper.toEntity(inputs, hashPassword, defaultRole));
	}

	public AuthInfo signIn(AccountSignIn inputs) {
		Account account = repo.findByUserNameIgnoreCase(inputs.userName().trim())
				.orElseThrow( () -> new BadCredentialsException("Bad Credentials: " + inputs.userName()));
		boolean match = encoder.matches(inputs.password(), account.getPassword());
		if (match) {
			return AccountMapper.toAuthInfo(jwt.create(account.getUserName(), account.getRoles()),account.getRoles());
		}
		throw new BadCredentialsException("Bad Credentials: " + inputs.userName());
	}

	public Account getAccount() {
		return repo.findAll().getFirst();
	}

}
