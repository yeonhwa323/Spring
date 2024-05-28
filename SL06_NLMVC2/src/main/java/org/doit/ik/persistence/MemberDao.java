package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;

public class MemberDao {
	
	// id에 해당하는 멤버 객체를 반환하는 메서드
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * "
				  + " FROM MEMBER "
				  + " WHERE id = ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		
		ResultSet rs = st.executeQuery();
		
		MemberVO member = null;
		
		if(rs.next())
		{
			member = new MemberVO();
			member.setId(rs.getString("id"));
			member.setPwd(rs.getString("pwd"));
			member.setName(rs.getString("name"));
			member.setGender(rs.getString("gender"));
			member.setBirth(rs.getString("birth"));
			member.setIs_lunar(rs.getString("is_lunar"));
			member.setCphone(rs.getString("cphone"));
			member.setEmail(rs.getString("email"));
			member.setHabit(rs.getString("habit"));
			member.setRegdate(rs.getDate("regdate"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return member;
	}
	
	// 회원가입 메서드
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException
	{
		String sql = "INSERT INTO MEMBER "
				+ "( id, pwd, name, gender, birth, is_lunar, cphone, email, habit, regdate) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, member.getId());
		st.setString(2, member.getPwd());
		st.setString(3, member.getName());
		st.setString(4, member.getGender());
		st.setString(5, member.getBirth());
		st.setString(6, member.getIs_lunar());
		st.setString(7, member.getCphone());
		st.setString(8, member.getEmail());
		st.setString(9, member.getHabit());
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}
}
