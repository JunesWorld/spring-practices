package com.junesworld.emaillist.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.junesworld.emaillist.vo.EmaillistVo;

@Repository
public class EmaillistRepository {

	public Boolean insert(EmaillistVo vo) {
		boolean result = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			String sql = 
				" insert into emaillist" +
				" values(null, '"+ vo.getFirstName() + "', '" + vo.getLastName() + "', '" + vo.getEmail() + "')";
			int count = stmt.executeUpdate(sql);
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public List<EmaillistVo> findAll() {
		List<EmaillistVo> result = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
					
			stmt = conn.createStatement();

			String sql = 
					" select first_name, last_name, email" + 
					" from emaillist order by no desc";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				String email = rs.getString(3);

				EmaillistVo vo = new EmaillistVo();
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.0.107:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
		return conn;
	}
}
