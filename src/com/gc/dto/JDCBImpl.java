package com.gc.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.gc.dao.Dao;

public class JDCBImpl implements Dao {
	private static final String url = "jdbc:mysql://localhost:3306/coffeecustomers";
	private static final String dbuserName = "jennaprice";
	private static final String dbpassword = "jennaprice";
	
	
	@Override
	public void insert(DBRecordDto record) {
		if(record.getClass().equals(new UserDto().getClass())) {
			
			UserDto userToInsert = (UserDto) record;
			String pw_hash = BCrypt.hashpw(userToInsert.getEntered_password(), BCrypt.gensalt(12));
			userToInsert.setEntered_password("");
			try {
			// Step # 2: Load and register the driver
			Class.forName("com.mysql.jdbc.Driver");
	
			// Step # 3 : Create Connection
			Connection con = DriverManager.getConnection(url, dbuserName, dbpassword);
			
			// instead of hardcoding these values we can use a prepared statement
			String preparedSql = "insert into customer(fullname, emailaddress, password, optin)" + "values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(preparedSql);
			ps.setString(1, userToInsert.getFullName());
			ps.setString(2, userToInsert.getEmailAddress());
			ps.setString(3, pw_hash);
			ps.setString(4, "0");
			ps.execute();
			
			}
			
			catch(Exception e) {
				
			}
		}
		

	}

	@Override
	public ArrayList<DBRecordDto> listAll() {
		ArrayList<DBRecordDto> itemList = new ArrayList<DBRecordDto>();
		// Step # 2: Load and register the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		// Step # 3 : Create Connection
		Connection con = DriverManager.getConnection(url, dbuserName, dbpassword);
		
		// instead of hardcoding these values we can use a prepared statement
		String preparedSql = "select * from product";
		PreparedStatement ps = con.prepareStatement(preparedSql);	
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("In result set");
			itemList.add(new ItemDto(rs.getString(2),rs.getString(3), rs.getInt(4), rs.getDouble(5)));
		}
		
		con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		
		return itemList;
	}

	@Override
	public void update(DBRecordDto record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DBRecordDto record) {
		// TODO Auto-generated method stub

	}
	
	public DBRecordDto searchByEmail(UserDto searchObj) throws SQLException, ClassNotFoundException {
	
		// Step # 2: Load and register the driver
		Class.forName("com.mysql.jdbc.Driver");

		// Step # 3 : Create Connection
		Connection con = DriverManager.getConnection(url, dbuserName, dbpassword);
		
		// instead of hardcoding these values we can use a prepared statement
		String preparedSql = "select * from customer where emailaddress = ?";
		PreparedStatement ps = con.prepareStatement(preparedSql);
		ps.setString(1, searchObj.getEmailAddress());
		ResultSet rs = ps.executeQuery();
		
		
		String fullName =null; 
		String pw_hash =null;
		
		while(rs.next()) {
			
				fullName = rs.getString(2);	
				pw_hash= rs.getString(4);
				searchObj.setFullName(fullName);
				searchObj.setPw_hash(pw_hash);
			
		}
		con.close();
		return searchObj;
	}

}
