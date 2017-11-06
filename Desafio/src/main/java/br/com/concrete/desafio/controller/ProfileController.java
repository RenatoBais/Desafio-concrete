package br.com.concrete.desafio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.concrete.desafio.dto.ResponseDTO;
import br.com.concrete.desafio.dto.UserDTO;
import br.com.concrete.desafio.exception.NotAuthorizedException;
import br.com.concrete.desafio.exception.SessionExpiredException;
import br.com.concrete.desafio.security.JwtConstants;
import br.com.concrete.desafio.service.UserService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/{id}")
	public @ResponseBody ResponseEntity<?> validateProfile(@PathVariable("id") String id, HttpServletRequest request) {
		try {
			String token = request.getHeader(JwtConstants.header);
			
			UserDTO userFromProfile =  userService.validateProfile(token, id);
			
			return new ResponseEntity<UserDTO>(userFromProfile, HttpStatus.ACCEPTED);
			
		}catch(SessionExpiredException se) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(se.getMessage()), HttpStatus.UNAUTHORIZED);
		}catch(NotAuthorizedException ne) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(ne.getMessage()), HttpStatus.UNAUTHORIZED);
		}catch(Exception e) {
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
