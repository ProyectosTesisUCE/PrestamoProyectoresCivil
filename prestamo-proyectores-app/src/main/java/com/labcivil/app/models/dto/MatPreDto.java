package com.labcivil.app.models.dto;

public class MatPreDto {
	
	private Long presMat;
	
	private String presNombre;
	
	private Long presNum;
		
	public MatPreDto(Long presMat, String presNombre, Long presNum) {
		this.presMat = presMat;
		this.presNombre = presNombre;
		this.presNum = presNum;
	}
	

	public Long getPresMat() {
		return presMat;
	}

	public void setPresMat(Long presMat) {
		this.presMat = presMat;
	}

	public String getPresNombre() {
		return presNombre;
	}

	public void setPresNombre(String presNombre) {
		this.presNombre = presNombre;
	}

	public Long getPresNum() {
		return presNum;
	}

	public void setPresNum(Long presNum) {
		this.presNum = presNum;
	}

	
}
