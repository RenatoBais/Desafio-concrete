package br.com.concrete.desafio.exception;

public class SessionExpiredException extends RuntimeException{

	private static final long serialVersionUID = -2877678415689894908L;

	public SessionExpiredException() {
		super("Sessão inválida!");
	}
	
}
