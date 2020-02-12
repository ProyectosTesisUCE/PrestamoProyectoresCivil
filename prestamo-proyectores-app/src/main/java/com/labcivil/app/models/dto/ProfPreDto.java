package com.labcivil.app.models.dto;

public class ProfPreDto {
	
	private Long presProf;
	
	private String presApellido;
	
	private Long presNum;
		
	public ProfPreDto(Long presProf, String presApellido, Long presNum) {
		this.presProf = presProf;
		this.presApellido = presApellido;
		this.presNum = presNum;
	}
	
	public Long getPresProf() {
		return presProf;
	}

	public void setPresProf(Long presProf) {
		this.presProf = presProf;
	}


	public String getPresApellido() {
		return presApellido;
	}

	public void setPresApellido(String presApellido) {
		this.presApellido = presApellido;
	}

	public Long getPresNum() {
		return presNum;
	}

	public void setPresNum(Long presNum) {
		this.presNum = presNum;
	}

	@Override
	public String toString() {
		return "ProfPreDto [presProf=" + presProf + ", presApellido=" + presApellido + ", presNum=" + presNum + "]";
	}
	
}
