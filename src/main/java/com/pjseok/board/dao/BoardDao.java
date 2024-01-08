package com.pjseok.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardDao {
	
	DataSource dataSource;
	
	
	
	
	public BoardDao() {
	      
	      try {
	         Context context = new InitialContext();
	         dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracledb");
	      } catch (NamingException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	   }


	   public void write(String bname, String btitle, String bcontent) {
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         conn = dataSource.getConnection();
	         String sql = "insert into fboard(bnum, bname, btitle, bcontent, bhit) values(fboard_seq.nextval,?,?,?,0)";
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, bname);
	         pstmt.setString(2, btitle);
	         pstmt.setString(3, bcontent);
	         
	         pstmt.executeUpdate();
	         
	      } catch (SQLException e) {         
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         try {
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(conn != null) {
	               conn.close();
	            }
	         } catch(Exception e) {
	            e.printStackTrace();
	         }
	            
	      }
	      
	   }
	   
	}