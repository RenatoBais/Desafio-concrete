package br.com.concrete.desafio.exception;

public class PasswordNotValidException extends RuntimeException {
	
	private static final long serialVersionUID = -8687896321430703746L;

	public PasswordNotValidException() {
		super("Usuário e/ou senha inválidos!");
	}
	
}
