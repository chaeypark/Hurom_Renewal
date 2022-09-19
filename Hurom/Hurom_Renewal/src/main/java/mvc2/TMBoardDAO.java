package mvc2;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

//DBConnPool을 통해 db에 연결하면 dao의 생성자가 만들어지고 
//생성자 안에는 조상 클래스인 DBConnPool의 내용물을 그대로 가져옴 
public class TMBoardDAO extends DBConnPool {

	public TMBoardDAO() {
		super();
	}

	
	// 게시물의 개수
	// 목록9
	public int selectCount(Map<String, Object> map) {
		
		int totalCount = 0;
		
		// 목록10
		// 쿼리문 생성
		String query = "SELECT COUNT(*) FROM PRODUCT WHERE LIKE 'tea%' ";

		// 목록11
		// 검색 조건이 있으면 해당 쿼리문을 붙여서 실행
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%'";
		}

		try {
			// 목록12. 쿼리문 생성 및 실행
			stmt = con.createStatement(); // 쿼리문 생성
			rs = stmt.executeQuery(query); // 쿼리문 실행
			rs.next(); // 다음값이 있는지 확인하고
			totalCount = rs.getInt(1); // 검색된 게시물의 개수 저장

		} catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}

		// 목록13. 결과값 반환
		return totalCount; // 게시물 개수 반환
	}
	
	
	

	// 목록19 게시물 목록 반환(페이징 기능)
	public List<JuicerBoardDTO> selectListPage(Map<String, Object> map){
		
		//List컬렉션: 순서O, 중복O/ ex)식당대기명단
		List<JuicerBoardDTO> board = new Vector<JuicerBoardDTO>();
		System.out.println("--------------------------------------");
		
		//목록 20 쿼리문 생성
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!! DB문에 인파라미터가 없는데 넣어서 실행이 안된거같아요.
		
		/*
		 * String query = " select product_num, product_name, p_cat, url_add " +
		 * " from product " + " where p_cat = 'juice_maker' ";
		 */
		
//		String query = " SELECT * FROM ( "
//					+ "	SELECT tb.*, ROWNUM rNum FROM ( "
//					+ "		SELECT * FROM PRODUCT 	";
//				
//				 if (map.get("searchWord") != null) { 
//					 query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
//				 }
//				 
//				 query +=  "ORDER BY product_num DESC "
//					+ "	) tb "
//					+ " )"
//					+ " WHERE rNum BETWEEN ? AND ? ";
		
		String query = " SELECT PRODUCT_NUM, PRODUCT_NAME, P_CAT, URL_ADD FROM ( "
				+ "	SELECT tb.*, ROWNUM rNum FROM ( "
				+ "		SELECT * FROM PRODUCT WHERE P_CAT LIKE 'tea%' 	";
			
			 if (map.get("searchWord") != null) { 
				 query += " WHERE " + map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' ";
			 }
			 
			 query +=  "ORDER BY product_num DESC "
				+ "	) tb "
				+ " )"
				+ " WHERE rNum BETWEEN ? AND ? ";
		
				 
		
		
				 
				 
				 
			   System.out.println(query);
			   System.out.println("----70----------------------------------");
		try {
			//목록21 동적 쿼리문 생성 및 실행
			psmt = con.prepareStatement(query); //동적 쿼리문 생성
			psmt.setString(1, map.get("start").toString()); //인파라미터 설정
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery(); //쿼리문 실행
			
			//목록22 반환된 게시물 목록을 <List>컬렉션에 바로 값을 넣을 수 없어서 
			//dto생성
			while (rs.next()) {
				JuicerBoardDTO dto = new JuicerBoardDTO();
				
				dto.setProduct_num(rs.getString(1));
				dto.setProduct_name(rs.getString(2));
				dto.setP_cat(rs.getString(3));
				dto.setUrl_add(rs.getString(4));
				
				board.add(dto);
			}

		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생1");
			e.printStackTrace();
		}
		//목록23 게시물 목록 결과값을 반환
		return board;
	}
	public int insertWrite(JuicerBoardDTO dto) {
		int result = 0;
		try {
			
			//글쓰기 22. 쿼리문 작성하고 실행하기
			String query = "INSERT INTO test1.product ( "
						 + " product_num, product_name, p_cat, url_add,) "
						 + " VALUES (?,?,?,?)";
			
			//인파라미터 설정
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getProduct_num());
			psmt.setString(2, dto.getProduct_name());
			psmt.setString(3, dto.getP_cat());
			psmt.setString(4, dto.getUrl_add());
			
			
			//쿼리실행
			result = psmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("쥬서제품 게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		//글쓰기23 쿼리문 실행 결과값 반환 
		return result;
	}
	
	//상세보기10. 일련번호에 해당하는 게시물을 DTO로 담아서 반환
	//삭제19. 삭제 전에 미리 정보를 담아두기 위해 selectView()실행
	//수정23. 수정 전에 미리 정보를 담아두기 위해 selectView()실행
	public JuicerBoardDTO selectView(String idx) {
		
		//상세보기11. DTO생성
		JuicerBoardDTO dto = new JuicerBoardDTO();
		
		
		//상세보기12. 쿼리문 양식 준비
		String query ="SELECT * FROM test1.Juicerboard WHERE idx=?";
		
		//상세보기13. 쿼리문 준비하고 설정하고 실행하고
		try {
			psmt= con.prepareStatement(query); //쿼리문 실행준비
			psmt.setString(1, idx); //인파라미터 설정
			rs = psmt.executeQuery(); //쿼리문 실행
			
			//상세보기14. 결과를 DTO객체에 저장
			if(rs.next()) {
				dto.setProduct_num(rs.getString(1));
				dto.setProduct_name(rs.getString(2));
				dto.setP_cat(rs.getString(3));
				dto.setUrl_add(rs.getString(4));
		
				
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
	
	
	
	//다운로드15. 다운로드 횟수 증가 
	public void downCountPlus(String idx) {
		
		//다운로드16. 쿼리 생성하고 처리  다운로드 끝
		String sql = "UPDATE test1.product"
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
						+" FROM test1.csmainboard "
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
			String query = "DELETE FROM test1.csmainboard WHERE idx = ?";
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
	public int updatePost(CSMainBoardDTO dto) {
		
		//수정58 결과값 담기위한 변수생성
		int result = 0;
		try {
			//수정59 쿼리문 작성하고 준비해서 인파라미터 채움
			String query = "UPDATE test1.csmainboard "
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









