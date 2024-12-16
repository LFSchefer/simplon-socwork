package co.simplon.socwork.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.simplon.socwork.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	String GET_DEFAULT_ROLE = "SELECT * FROM t_roles WHERE \"default\" = true";
	
	@Query(value = GET_DEFAULT_ROLE, nativeQuery = true)
	Optional<Role> getDefaultRole();
}
