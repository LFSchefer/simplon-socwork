package co.simplon.socwork.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.socwork.dtos.AccountCreate;
import co.simplon.socwork.dtos.AccountSignIn;
import co.simplon.socwork.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AcountController {

	private final AccountService service;
	
	public AcountController(AccountService service) {
		this.service = service;
	}


	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void create(@RequestBody AccountCreate inputs) {
		service.create(inputs);
	}
	
	@PostMapping("/sign-in")
	public ResponseEntity<String> signIn(@RequestBody AccountSignIn inputs) {
		return ResponseEntity.ok(service.signIn(inputs));

	}
}
