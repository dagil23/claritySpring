package com.clarity.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clarity.spring.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario,Long > {
	
	Usuario findByEmail(String email);
	Optional<Usuario> findByEmailContainsIgnoreCase(String email);
	boolean existsByemail(String email);
	
}
