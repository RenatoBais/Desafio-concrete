package br.com.concrete.desafio.dto;

public class ResponseDTO {

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public ResponseDTO(String mensagem) {
		this.mensagem = mensagem;
	}

}
