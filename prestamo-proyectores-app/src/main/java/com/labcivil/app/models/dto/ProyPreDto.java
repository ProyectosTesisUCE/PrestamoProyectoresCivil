package com.labcivil.app.models.dto;

public class ProyPreDto {
	
	private Long presPro;
	
	private String presAlta;
	
	private Long presNum;
	

	public ProyPreDto(Long presPro, String presAlta, Long presNum) {
		this.presPro = presPro;
		this.presAlta = presAlta;
		this.presNum = presNum;
	}

	public Long getPresPro() {
		return presPro;
	}

	public void setPresPro(Long presPro) {
		this.presPro = presPro;
	}

	public String getPresAlta() {
		return presAlta;
	}

	public void setPresAlta(String presAlta) {
		this.presAlta = presAlta;
	}


	public Long getPresNum() {
		return presNum;
	}

	public void setPresNum(Long presNum) {
		this.presNum = presNum;
	}

	@Override
	public String toString() {
		return "ProyPreDto [presPro=" + presPro + ", presAlta=" + presAlta + ", presNum=" + presNum + "]";
	}

}
