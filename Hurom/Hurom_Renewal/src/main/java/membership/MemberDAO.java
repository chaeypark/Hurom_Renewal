package membership;


import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;

import common.DBConnPool;

public class MemberDAO extends DBConnPool{
	public MemberDAO() {
		super();
	}
	

	public int InsertMemberInfo(MemberDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO membership (memberid, memberpw, email, membertel, membernum, gender, reqired, optional, birthdate, membername) values(?,?,?,?,member_num_seq.nextVal, ?, ?, ? ,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getMemberid());
			psmt.setString(2, dto.getMemberpw());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getMembertel());
			psmt.setString(5, dto.getGender());
			psmt.setString(6, dto.getRequired());
			psmt.setString(7, dto.getOptional());
			psmt.setDate(8, dto.getBirthdate());
			psmt.setString(9, dto.getMembername());
			
			result = psmt.executeUpdate();
			
			
		}catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("중복된 아이디");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("알 수 없는 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public MemberDTO MemberValidate(String id, String pw) {//로그인해 있는 경우에도 사용하기 편하게 이름을 범용성 있게 변경
		
		
		MemberDTO dto = new MemberDTO();
		try {
			String query = "SELECT * FROM membership where MEMBERID = ? and MEMBERPW = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setMemberid(id);
				dto.setMemberpw(pw);
				dto.setEmail(rs.getString(3));
				dto.setSignupdate(rs.getDate(4));
				dto.setMembertel(rs.getString(5));
				dto.setMembernum(rs.getString(6));
				dto.setGender(rs.getString(7));
				dto.setRequired(rs.getString(8));
				dto.setOptional(rs.getString(9));
				dto.setBirthdate(rs.getDate(10));
				dto.setMembername(rs.getString(11));
			}
		}catch(Exception e) {
			System.out.println("로그인 중 문제 발생");
			e.printStackTrace();
		}
		return dto;
	}
}