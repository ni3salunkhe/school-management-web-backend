package com.api.service;

import java.util.List;
import com.api.entity.LeavingInfo;

public interface LeavingInfoService {
	
	public LeavingInfo post(LeavingInfo leavigInfo);
	
	public List<LeavingInfo> getdata();
	
	public LeavingInfo getbyid(long id);
	
	public void deletedata(long id);
}
