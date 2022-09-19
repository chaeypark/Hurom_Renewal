package mypageCon;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import membership.MemberDTO;
import mvc2.CSMainBoardDTO;

public class MypageDAO extends DBConnPool{
	
	public MypageDAO() {
		super();
	}
	
	public MemberDTO userEdit(String id) {		
		MemberDTO dto = new MemberDTO();
		try {
			String query = "SELECT * FROM membership where MEMBERID = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);		
			
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto.setMemberid(id);
				dto.setMemberpw(rs.getString(2));
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
	
	public int updateUser(MemberDTO dto) {		
		int result = 0;
		try {			
			String query = " UPDATE membership "
					+" SET membername=? , birthdate=?, membertel=?, email=?, gender=?, optional=?, memberpw=? "
					+ " where memberid=? ";
			
			psmt = con.prepareStatement(query);		
			
			psmt.setString(1, dto.getMembername());
			psmt.setDate(2, dto.getBirthdate());
			psmt.setString(3, dto.getMembertel());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getGender());					
			psmt.setString(6, dto.getOptional());
			psmt.setString(7, dto.getMemberpw());
			
			psmt.setString(8, dto.getMemberid());
			
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("회원정보수정 중 오류 / mypageDAO");
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteUser(String id) {        
        
        int result = 0;
        
        try {
            
            String query = "DELETE FROM membership WHERE memberid=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, id);
            result = psmt.executeUpdate();
            
        } catch(Exception e) {
            System.out.println("회원 삭제중 오류 발생");
            e.printStackTrace();
        }
        
        return result;
    }
//	---------------------
	//목록 9. 게시물의 개수
		public int selectCount(Map<String, Object>map) {
		int totalCount = 0;
		
		//목록10. 쿼리문 생성
		String query="SELECT COUNT(*) FROM mvcboard ";
		
		//목록11. 검색조건이 있으면
		if(map.get("serchWord") != null) {
			query +=" WHERE "+ map.get("searchField")
				  +" LIKE '%"+map.get("serchWord")+"%'";
		}
		
		try {
			//목록12. 쿼리문 생성 및 실행
			stmt = con.createStatement();//쿼리문 생성
			rs= stmt.executeQuery(query);//쿼리문 실행
			rs.next();
			totalCount = rs.getInt(1); // 검색된 게시글물의 개수 저장
		}catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		//목록13. 결과값 반환
		return totalCount; // 게시물 개수 반환
	}
	
	//목록19. 게시물 목록 반환(페이징 기능)
	public List<CSMainBoardDTO> selectListPage(Map<String, Object>map){
		
		//list 컬렉션 : 순서O, 중복O ex)식당대기명단
		List<CSMainBoardDTO> board = new Vector<CSMainBoardDTO>();
		
		//목록20. 쿼리문 생성
		String query = "SELECT*FROM("					
					 +"	SELECT tb.* , ROWNUM rNum FROM ("
					 +"		SELECT * FROM mvcboard ";
		
		if(map.get("searchWord")!=null){
			query +=" 		WHERE "+ map.get("searchField")
				  +" 		LIKE '%"+map.get("searchWord")+ "%'";
		}
		
			query +="		ORDER BY idx DESC"
	              +"	) tb"
	              +" )"
	              +" WHERE rNum BETWEEN ? AND ?";
			
		try {
			//목록21. 동적 쿼리문 생성 및 실행
			psmt = con.prepareStatement(query); //동적 쿼리문 설정
			psmt.setString(1, map.get("start").toString()); //인파라미터 설정
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();//쿼리문 실행
			
			//목록22. 반환된 게시글 목록을 List컬렉션에 추가
			while (rs.next()) {
				CSMainBoardDTO dto = new CSMainBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);						
			}
			
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();			
		}
		
		//목록23. 게시물 목록 결과값을 반환
		return board;		
	}
	
	public boolean confirmPassword(String pass, String id) {
		//삭제9. 결과값 담을 변수 선언
		//수정9
		boolean isCorr = true;
		
		//삭제10. 비밀번호와 일련변호가 일치하는 게시물의 개수를 세어서 비밀번호 일치 여부를 확인
		//수정10.
		try {
			String sql ="SELECT count(*)"
						+" FROM membership "
						+" WHERE memberpw=? AND memberid=? ";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, id);
			
			rs = psmt.executeQuery();
			rs.next();
			
			//삭제11. 실행결과가 0이면(일치하는 게시물이 없으면) false 반환
			//수정11.
			if(rs.getInt(1) == 0) {
				isCorr = false;
			}
		}catch(Exception e) {
			isCorr = false;
			e.printStackTrace();
		}

		//삭제12. 결과값 반환
		//수정12
		return isCorr;
	}
}
