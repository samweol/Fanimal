package com.project.care;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.care.DBUtil;

public abstract class DML {

	public int executeUpdate(String sql) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = DBUtil.open();
			pstat = conn.prepareStatement(sql); 
			
			setParameters(pstat);
			
			return pstat.executeUpdate();
			
		} finally {
			if (pstat != null) {
				pstat.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		}	
		
	}
		
	
	public abstract void setParameters(PreparedStatement pstat) throws SQLException;
	
	
}
