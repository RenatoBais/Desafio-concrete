package br.com.concrete.desafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.concrete.desafio.dto.ResponseDTO;
import br.com.concrete.desafio.dto.UserDTO;
import br.com.concrete.desafio.exception.PasswordNotValidException;
import br.com.concrete.desafio.exception.UserNotExistsException;
import br.com.concrete.desafio.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
		 
		try {
			
			UserDTO userLogin = userService.loginUser(userDTO);
			
			return new ResponseEntity<UserDTO>(userLogin, HttpStatus.ACCEPTED);
		
		}catch(UserNotExistsException une) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(une.getMessage()), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}catch(PasswordNotValidException pne) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(pne.getMessage()), HttpStatus.UNAUTHORIZED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
