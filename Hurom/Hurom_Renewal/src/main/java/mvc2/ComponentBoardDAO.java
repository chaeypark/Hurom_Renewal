package mvc2;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.websocket.Session;

import common.DBConnPool;
//DBConnPool을 통해 db에 연결
public class ComponentBoardDAO extends DBConnPool{

	public ComponentBoardDAO() {
		super();
	}
	
	// 게시물의 개수
	//목록9
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		//목록10
		//쿼리문 생성
		String query = "SELECT COUNT(*) FROM test1.componentboard ";
		
		//목록11 
		// 검색 조건이 있으면 해당 쿼리문을 붙여서 실행
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
				  + " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			//목록12. 쿼리문 생성 및 실행
			stmt = con.createStatement(); //쿼리문 생성
			rs = stmt.executeQuery(query); //쿼리문 실행
			rs.next(); //다음값이 있는지 확인하고 
			totalCount = rs.getInt(1); //검색된 게시물의 개수 저장
			
		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		//목록13. 결과값 반환
		return totalCount; //게시물 개수 반환
	}
	/////////////////////////////////////////////////////////////////
	//목록19 게시물 목록 반환(페이징 기능)
	public List<ComponentBoardDTO> selectListPage(Map<String, Object> map){
		
		//List컬렉션: 순서O, 중복O/ ex)식당대기명단
		List<ComponentBoardDTO> board = new Vector<ComponentBoardDTO>();
		
		
		//목록 20 쿼리문 생성
		String query = " SELECT * FROM ( "
					 + " 	SELECT tb.*, ROWNUM rNum FROM ( " //ROWNUM은 오라클에 원래 있는기능
					 + " 		SELECT * FROM test1.componentboard ";
		
		if (map.get("searchWord") != null) {
			   query += " 		WHERE " + map.get("searchField")
			   		 + " 		LIKE '%" + map.get("searchWord") + "%'";
		}
		
			   query += " 		ORDER BY idx DESC "
					 + "	) tb "
					 + " ) "
					 + " WHERE rNum BETWEEN ? AND ?"; //몇번부터 몇번까지 보일지 구간지정
		
		try {
			//목록21 동적 쿼리문 생성 및 실행
			psmt = con.prepareStatement(query); //동적 쿼리문 생성
			psmt.setString(1, map.get("start").toString()); //인파라미터 설정
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery(); //쿼리문 실행
			
			//목록22 반환된 게시물 목록을 <List>컬렉션에 바로 값을 넣을 수 없어서 
			//dto생성
			while (rs.next()) {
				ComponentBoardDTO dto = new ComponentBoardDTO();
				
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
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		//목록23 게시물 목록 결과값을 반환
		return board;
	}
	///////////////////////////////////////////////////////////////////////
	//글쓰기21. 게시글을 받아 DB에 추가
	public int insertWrite(ComponentBoardDTO dto) {
		int result = 0;
		try {
			
			//글쓰기 22. 쿼리문 작성하고 실행하기
			String query = "INSERT INTO test1.componentboard ( "
						 + " idx, name, title, content, ofile, sfile, pass, memberid) "
						 + " VALUES ( seq_board_num.nextval, ?,?,?,?,?,?,?)";
			
			//인파라미터 설정
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			psmt.setString(7, dto.getMemberid());
			
			
			//쿼리실행
			result = psmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		//글쓰기23 쿼리문 실행 결과값 반환 
		return result;
	}
	
	//상세보기10. 일련번호에 해당하는 게시물을 DTO로 담아서 반환
	//삭제19. 삭제 전에 미리 정보를 담아두기 위해 selectView()실행
	//수정23. 수정 전에 미리 정보를 담아두기 위해 selectView()실행
	public ComponentBoardDTO selectView(String idx) {
		
		//상세보기11. DTO생성
		ComponentBoardDTO dto = new ComponentBoardDTO();
		
		
		//상세보기12. 쿼리문 양식 준비
		String query ="SELECT * FROM test1.componentboard WHERE idx=?";
		
		//상세보기13. 쿼리문 준비하고 설정하고 실행하고
		try {
			psmt= con.prepareStatement(query); //쿼리문 실행준비
			psmt.setString(1, idx); //인파라미터 설정
			rs = psmt.executeQuery(); //쿼리문 실행
			
			//상세보기14. 결과를 DTO객체에 저장
			if(rs.next()) {
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
				
			}
		}catch (Exception e) {
			System.out.println("게시글 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		//상세보기 15. 결과값반환
		//삭제19. 메서드 실행 후 결과값 반환
		//수정24. 메소드 실행 후 결과값 반환
		return dto;
	}
	
	//상세보기6. 게시글의 조회수 증가하기
	public void updateVisitCount(String idx) {	
		
		//상세보기7. 쿼리문 작성 
		String query = "UPDATE test1.componentboard " 
					+ " SET visitcount = visitcount + 1 "
					+ " WHERE idx = ?";
		
		
		//상세보기8. 쿼리문 준비 후 실행
		try{
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeQuery(); //psmt.executeUpdate();도 사용가능 
			
		}catch(Exception e) {
			System.out.println("게시글 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	
	}
	
	//다운로드15. 다운로드 횟수 증가 
	public void downCountPlus(String idx) {
		
		//다운로드16. 쿼리 생성하고 처리  다운로드 끝
		String sql = "UPDATE test1.componentboard"
					+" SET downcount = downcount + 1 "
					+" WHERE idx=?";
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("다운로드 중 예외발생");
			e.printStackTrace();
		}
	}
	
	//삭제8. 입력한 비밀번호가 게시물의 비밀번호와 동일한지 확인
	//수정8
	public boolean confirmPassword(String pass, String idx) {
		//삭제9. 결과값 담을 변수 선언
		//수정9
		boolean isCorr = true;
		
		//삭제10. 비밀번호와 일련번호가 일치하는 게시물의 개수를 세어서
		//수정10
		//비밀번호 일치 여부를 확인
		try {
			String sql = "SELECT count(*) "
						+" FROM test1.componentboard "
						+" WHERE pass=? AND idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			
			rs.next();
			
			//삭제11. 실행결과가 0이면 (일치하는 게시물이 없으면) false반환
			//수정11
			if(rs.getInt(1) == 0 ) {
				isCorr = false;
			}
		}catch (Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		
		//삭제12. 결과값 반환
		//수정12
		return isCorr;
	}

	//삭제21. 지정된 일련번호의 게시물을 삭제
	public int deletePost(String idx) {
		
		//삭제22. 결과값 담을 변수 생성
		int result = 0;
		try {
			//삭제23.
			String query = "DELETE FROM test1.componentboard WHERE idx = ?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("게시글 삭제 중 예외 발생");
			e.printStackTrace();
		}
		//삭제24.
		return result;
	}
	
	//게시글 데이터를 받아 DB에 저장된 내용을 수정
	//수정57
	public int updatePost(ComponentBoardDTO dto) {
		
		//수정58 결과값 담기위한 변수생성
		int result = 0;
		try {
			//수정59 쿼리문 작성하고 준비해서 인파라미터 채움
			String query = "UPDATE test1.componentboard "
					 + " SET title=?, name=?, content=?, ofile=?, sfile=? "
					 + " WHERE idx=? and pass=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시글 수정 중 예외 발생");
			e.printStackTrace();
		}
		//수정60 결과값을 반환
		return result;
	}
}







