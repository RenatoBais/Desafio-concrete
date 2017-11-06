package br.com.concrete.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.concrete.desafio.dto.PhoneDTO;
import br.com.concrete.desafio.mapper.PhoneMapper;
import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.repository.PhoneRepository;

@Service
public class PhoneService {

	@Autowired
	PhoneRepository phoneRepository;
	
	public PhoneDTO save(PhoneDTO phoneDTO) {
		Phone phone = phoneRepository.save(PhoneMapper.mapToEntity(phoneDTO));
		return PhoneMapper.mapToDTO(phone);
	}
	
}
