package br.com.concrete.desafio.mapper;

import br.com.concrete.desafio.dto.PhoneDTO;
import br.com.concrete.desafio.model.Phone;

public class PhoneMapper {

	public static Phone mapToEntity(PhoneDTO phoneDTO) {
		
		Phone phone = new Phone();
		phone.setPhoneId(phoneDTO.getPhoneId());
		phone.setDdd(phoneDTO.getDdd());
		phone.setNumber(phoneDTO.getNumber());
		
		return phone;
		
	}
	
	public static PhoneDTO mapToDTO(Phone phone) {
		
		PhoneDTO phoneDTO = new PhoneDTO();
		phoneDTO.setPhoneId(phone.getPhoneId());
		phoneDTO.setDdd(phone.getDdd());
		phoneDTO.setNumber(phone.getNumber());
		
		return phoneDTO;
		
	}
	
}
