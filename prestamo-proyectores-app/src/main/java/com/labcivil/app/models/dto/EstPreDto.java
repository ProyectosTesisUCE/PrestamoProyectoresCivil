package com.labcivil.app.models.dto;

public class EstPreDto {
	
	private Long presEst;
	
	private String presApellido;
	
	private Long presNum;
		

	public EstPreDto(Long presEst, String presApellido, Long presNum) {
		this.presEst = presEst;
		this.presApellido = presApellido;
		this.presNum = presNum;
	}

	public Long getPresEst() {
		return presEst;
	}

	public void setPresEst(Long presEst) {
		this.presEst = presEst;
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
		return "EstPreDto [presEst=" + presEst + ", presApellido=" + presApellido + ", presNum=" + presNum + "]";
	}
	
}
