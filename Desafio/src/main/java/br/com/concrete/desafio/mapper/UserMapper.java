package br.com.concrete.desafio.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.concrete.desafio.dto.PhoneDTO;
import br.com.concrete.desafio.dto.UserDTO;
import br.com.concrete.desafio.model.Phone;
import br.com.concrete.desafio.model.User;

public class UserMapper {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static UserDTO mapToDTO(User user) {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setCreated(user.getCreated().format(formatter));
		userDTO.setModified(user.getModified().format(formatter));
		userDTO.setLast_login(user.getLast_login().format(formatter));
		userDTO.setToken(user.getToken());
		
		List<PhoneDTO> phoneList = new ArrayList<PhoneDTO>();
		
		for (Phone phone : user.getPhones()) {
			phoneList.add(PhoneMapper.mapToDTO(phone));
		}
		
		userDTO.setPhones(phoneList);
		
		return userDTO;
		
	}
	
	public static User mapToEntity(UserDTO userDTO) {
		
		User user = new User();
		
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setCreated(LocalDate.parse(userDTO.getCreated(), formatter));
		user.setModified(LocalDate.parse(userDTO.getModified(), formatter));
		user.setLast_login(LocalDate.parse(userDTO.getLast_login(), formatter));
		user.setToken(userDTO.getToken());
		
		List<Phone> phoneList = new ArrayList<Phone>();
		
		for (PhoneDTO phoneDTO : userDTO.getPhones()) {
			phoneList.add(PhoneMapper.mapToEntity(phoneDTO));
		}
		
		user.setPhones(phoneList);
		
		return user;
	}
	
}
