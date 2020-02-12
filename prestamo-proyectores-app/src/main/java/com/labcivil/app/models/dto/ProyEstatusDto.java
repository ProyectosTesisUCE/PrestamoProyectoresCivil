package com.labcivil.app.models.dto;

public class ProyEstatusDto {
	
	
	private String estatusProy;
	
	private Long countEstatus;

	public ProyEstatusDto(String estatusProy, Long countEstatus) {
		this.estatusProy = estatusProy;
		this.countEstatus = countEstatus;
	}

	public String getEstatusProy() {
		return estatusProy;
	}

	public void setEstatusProy(String estatusProy) {
		this.estatusProy = estatusProy;
	}

	public Long getCountEstatus() {
		return countEstatus;
	}

	public void setCountEstatus(Long countEstatus) {
		this.countEstatus = countEstatus;
	}

	@Override
	public String toString() {
		return "ProyEstatusDto [estatusProy=" + estatusProy + ", countEstatus=" + countEstatus + "]";
	}
	
}
