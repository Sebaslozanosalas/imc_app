package com.sls.imc_app.model;

import java.time.LocalDateTime;

public class ImcRecord {
	
	private int id;
	private int userId;
	private LocalDateTime createdAt;
	private int currentAge;
	private double heightCm;
	private double weightKm;
	private double imc;
	
	
	public ImcRecord(int userId, int currentAge, double heightCm,
					 double weightKm, double imc) {
		
		this.userId = userId;
		this.createdAt = LocalDateTime.now();
		this.currentAge = currentAge;
		this.heightCm = heightCm;
		this.weightKm = weightKm;
		this.imc = imc;
		
	}
	
	
	public ImcRecord(int currentAge, double heightCm, double weightKm, double imc, LocalDateTime createdAt) {
		super();
		this.createdAt = createdAt;
		this.currentAge = currentAge;
		this.heightCm = heightCm;
		this.weightKm = weightKm;
		this.imc = imc;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public int getCurrentAge() {
		return currentAge;
	}


	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}


	public double getHeightCm() {
		return heightCm;
	}


	public void setHeightCm(double heightCm) {
		this.heightCm = heightCm;
	}


	public double getWeightKm() {
		return weightKm;
	}


	public void setWeightKm(double weightKm) {
		this.weightKm = weightKm;
	}


	public double getImc() {
		return imc;
	}


	public void setImc(double imc) {
		this.imc = imc;
	}
	
	
	
	

}
