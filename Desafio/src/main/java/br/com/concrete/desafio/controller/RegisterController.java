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
import br.com.concrete.desafio.exception.UserAlreadyExistsException;
import br.com.concrete.desafio.service.UserService;


@RestController
@RequestMapping("/api")
public class RegisterController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
		 
		try {
			
			UserDTO userRegistered = userService.registerUser(userDTO);
			
			return new ResponseEntity<UserDTO>(userRegistered, HttpStatus.CREATED);
		
		}catch(UserAlreadyExistsException uee) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(uee.getMessage()), HttpStatus.CONFLICT);
		}catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
