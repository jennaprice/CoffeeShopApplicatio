package com.gc.dao;

import java.util.ArrayList;
import java.util.List;
import com.gc.dto.DBRecordDto;

public interface Dao {

	//add-insert-create
	public void insert(DBRecordDto record);	
	
	//read- list
	public ArrayList<DBRecordDto> listAll();
	
	//update
	public void update(DBRecordDto record);
	
	//delete
	public void delete(DBRecordDto record);
	
	
}


