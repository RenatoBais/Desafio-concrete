package br.com.concrete.desafio.exception;

public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -2181461750220916704L;

	public NotAuthorizedException() {
		super("Não autorizado!");
	}
	
}
