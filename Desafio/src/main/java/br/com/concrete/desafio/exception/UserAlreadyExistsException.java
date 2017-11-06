package br.com.concrete.desafio.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 8882844705874457547L;

		public UserAlreadyExistsException() {
			super("Email já cadastrado!");
		}
	}
