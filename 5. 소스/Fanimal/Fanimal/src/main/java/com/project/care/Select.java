package com.project.care;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Select {

public Object executeQuery(String sql) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.open();
			pstat = conn.prepareStatement(sql); 
			
			setParameters(pstat);
			
			rs = pstat.executeQuery();
			
			return getResult(rs);
			
		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (pstat != null) {
				pstat.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		}
		
	}
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException ;	
	
	public abstract Object getResult(ResultSet rs) throws SQLException ;
	
	
	
	
}