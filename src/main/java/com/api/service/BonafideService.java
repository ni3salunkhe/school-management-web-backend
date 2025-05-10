package com.api.service;

import java.util.List;

import com.api.entity.Bonafide;

public interface BonafideService {
	
	public List<Bonafide> getAllData();
	
	public Bonafide getbyid(long id);
	
	public Bonafide postData(Bonafide bonafide);
	
	public void deleteData(long id);
	
}
