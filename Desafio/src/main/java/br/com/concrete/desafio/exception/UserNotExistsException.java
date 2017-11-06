package br.com.concrete.desafio.exception;

public class UserNotExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 7490177649546657957L;

	public UserNotExistsException() {
		super("Usuário e/ou senha inválidos!");
	}

}
