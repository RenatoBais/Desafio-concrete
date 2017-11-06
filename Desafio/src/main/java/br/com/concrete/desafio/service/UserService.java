package br.com.concrete.desafio.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.concrete.desafio.dto.PhoneDTO;
import br.com.concrete.desafio.dto.UserDTO;
import br.com.concrete.desafio.exception.NotAuthorizedException;
import br.com.concrete.desafio.exception.PasswordNotValidException;
import br.com.concrete.desafio.exception.SessionExpiredException;
import br.com.concrete.desafio.exception.UserAlreadyExistsException;
import br.com.concrete.desafio.exception.UserNotExistsException;
import br.com.concrete.desafio.mapper.UserMapper;
import br.com.concrete.desafio.model.User;
import br.com.concrete.desafio.repository.UserRepository;
import br.com.concrete.desafio.security.JwtTokenUtil;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PhoneService phoneService;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	
	public UserDTO registerUser(UserDTO userDTO) throws UserAlreadyExistsException {
		
			User userFound =  userRepository.findByEmail(userDTO.getEmail());
			
			if(userFound != null) {
				throw new UserAlreadyExistsException();
			}
			
			List<PhoneDTO> phones = new ArrayList<PhoneDTO>();
			for (PhoneDTO phoneDTO : userDTO.getPhones()) {
				phones.add(phoneService.save(phoneDTO));
			}
			
			LocalDate localDate = LocalDate.now();
			String date = localDate.format(formatter);

			userDTO.setCreated(date);
			userDTO.setLast_login(date);
			userDTO.setModified(date);
			userDTO.setPhones(phones);
			userDTO.setPassword(encoder.encodePassword(userDTO.getPassword(), null));
			userDTO.setToken(JwtTokenUtil.generateToken(userDTO));
			
			User userRegistered = userRepository.save(UserMapper.mapToEntity(userDTO));
		
			return UserMapper.mapToDTO(userRegistered);
			
		
	}

	public UserDTO loginUser(UserDTO userDTO) throws UserNotExistsException, PasswordNotValidException {
		
		User userFound =  userRepository.findByEmail(userDTO.getEmail());
		
		if(userFound == null) {
			throw new UserNotExistsException();
		}

		if(!encoder.isPasswordValid(userFound.getPassword(),userDTO.getPassword(), null)) {
			throw new PasswordNotValidException();
		}
			
		LocalDate localDate = LocalDate.now();
		userFound.setLast_login(localDate);
		userFound.setToken(JwtTokenUtil.generateToken(userDTO));
		
		User userLogged = userRepository.save(userFound);
			
		return UserMapper.mapToDTO(userLogged);
		
	}

	public UserDTO validateProfile(String token, String id) throws NotAuthorizedException {
		
		User userFromProfile =  userRepository.findById(id);
		
		if (userFromProfile == null) {
			throw new NotAuthorizedException();
		}
		
		if (!userFromProfile.getToken().equals(token)) {
			throw new NotAuthorizedException();
		}
		
		if(JwtTokenUtil.isTokenExpired(token)){
			throw new SessionExpiredException();
		}
		
		return UserMapper.mapToDTO(userFromProfile);
		
	}
}