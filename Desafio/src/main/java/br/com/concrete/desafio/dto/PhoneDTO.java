package br.com.concrete.desafio.dto;

import java.io.Serializable;

public class PhoneDTO implements Serializable{

	private static final long serialVersionUID = 7033727009457233620L;
	
	private Long phoneId;
	
	private Integer ddd;
	
	private Integer number;
	
	public PhoneDTO() {

	}

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
