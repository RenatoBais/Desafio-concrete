package br.com.concrete.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.concrete.desafio.model.User;

public interface UserRepository  extends CrudRepository<User, Long>{

	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User findById(String id);
	
}
