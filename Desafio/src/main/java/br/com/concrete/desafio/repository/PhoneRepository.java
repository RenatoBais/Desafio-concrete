package br.com.concrete.desafio.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.concrete.desafio.model.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long>{

}
