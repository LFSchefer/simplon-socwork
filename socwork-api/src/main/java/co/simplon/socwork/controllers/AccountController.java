package co.simplon.socwork.controllers;

import co.simplon.socwork.dtos.AuthInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountSignIn;
import co.simplon.socwork.entities.Account;
import co.simplon.socwork.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService service;
	
	public AccountController(AccountService service) {
		this.service = service;
	}


	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody AccountCreate inputs) {
		service.create(inputs);
	}
	
	@PostMapping("/sign-in")
	@ResponseStatus(code = HttpStatus.OK)
	public AuthInfo signIn(@RequestBody AccountSignIn inputs) {
		return service.signIn(inputs);
	}
	
	@GetMapping("/with-role")
	@ResponseStatus(code = HttpStatus.OK)
	public String withRole() {
		return "with role";
	}
	
	@GetMapping("/exo")
	@ResponseStatus(code = HttpStatus.OK)
	public Account exo() {
		return service.getAccount();
	}
}
