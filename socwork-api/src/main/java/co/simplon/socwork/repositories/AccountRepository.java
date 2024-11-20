package co.simplon.socwork.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.socwork.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account>  findByUserNameIgnoreCase(String email);

}
