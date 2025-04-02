package com.api.service;

import java.util.List;

import com.api.entity.Division;

public interface DivisionService {
	
	public Division post(Division division);
	
	public List<Division> getdata();
	
	public Division getbyid(long id);
	
	public void deletedata(long id);
	
}
