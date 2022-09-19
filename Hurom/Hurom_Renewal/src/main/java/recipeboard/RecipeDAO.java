package recipeboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import product.ProductDTO;
import recipeUpload.UserfileDAO;
import recipeUpload.UserfileDTO;

public class RecipeDAO extends DBConnPool{
	public RecipeDAO() {
		super();
	}
	
	public int insertBoard(RecipeDTO dto) {
		int result = 0;
		
		try {
			String query = "INSERT INTO myrecipe (idx, writer_id, title, content, product_name, thumnail) VALUES (?, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getIdx());
			psmt.setString(2, dto.getWriter_id());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getProduct_name());
			psmt.setString(6, dto.getThumnail());
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("레시피를 추가하던 중 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public String maxIdx() {
		String query = "SELECT MAX(idx) FROM MYRECIPE";
		String idx = "1";
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				idx = rs.getString(1);
			}
		}catch(Exception e) {
			System.out.print("MAX 찾기 실패");
			e.printStackTrace();
		}
		
		return idx;
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		// 목록 10. 쿼리문 생성
		String query = "SELECT COUNT(*) FROM MYRECIPE";
		// 목록 11. 검색 조건이 있는지 확인
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchFiled") + " LIKE  '%" + map.get("searchWord") + "%'";
		}
		
		try{
			//목록 12. 쿼리문 생성 및 실행
			stmt = con.createStatement(); //쿼리문 생성
			rs = stmt.executeQuery(query); //쿼리문 실행
			rs.next();
			totalCount = rs.getInt(1); //검색된 게시물의 개수 저장
		}catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		//목록 13. 결과값 반환
		return totalCount;
	}
	
	
	
	
	public List<RecipeDTO> recipeList(Map<String, Object> map){
		//List 컬랙션 : 순서 O 중복 O
		List<RecipeDTO> board = new Vector<RecipeDTO>();
		
		//목록 20.쿼리문 생성
		String query = "SELECT * FROM ("
				+ "	SELECT tb.*, ROWNUM rNum"
				+ "	FROM("
				+ "		SELECT * FROM myrecipe ";
		if (map.get("searchWord") != null) {
			query += " 		WHERE " + map.get("searchField")
					+ " 	LIKE UPPER('%" + map.get("searchWord") + "%')";
		}
		query += "			ORDER BY idx DESC"
		+ "		) tb"
		+ " )"
		+ "WHERE rNUM BETWEEN ? AND ?";
		
		try {
			
			//목록21. 동적 쿼리문 생성 및 실행
			psmt = con.prepareStatement(query); //동적 쿼리문 생성
			psmt.setString(1, map.get("start").toString()); //파라미터 설정
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			//목록22. 반환된 게시물 목록을 List컬렉션에 추가
			while(rs.next()) {
				RecipeDTO dto = new RecipeDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setThumnail(rs.getString("thumnail"));
				board.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		//목록23. 결과값을 반환
		return board;
	}
	
	public RecipeDTO selectView(String idx) {
		RecipeDTO dto = new RecipeDTO();
		
		String query = "SELECT * FROM myrecipe WHERE idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setIdx(rs.getString("idx"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setThumnail(rs.getString("thumnail"));
				
			}
			
		}catch(Exception e) {
			System.out.println("게시글 상세보기 중 오류");
			e.printStackTrace();
		}
		return dto;
	}
	
	public ProductDTO product_link(String product_name) {
		ProductDTO pdto = new ProductDTO();
		
		String query = "SELECT DISTINCT m.product_name, p.product_num"
					+ " FROM myrecipe m, product p"
					+ " WHERE m.product_name = p.product_name"
					+ " AND p.product_name = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, product_name);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
			pdto.setProduct_num(rs.getString("product_num"));
			}
			
		}catch(Exception e) {
			System.out.println("상품 이미지 링크 연결 중 예외 발생");
			e.printStackTrace();
		}
		return pdto;
		
	}
	
	public int UpdateEdit(RecipeDTO dto) {
		
		int result = 0;
		try {
			String query = "UPDATE myrecipe SET title=?, content=?, product_name=? WHERE idx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getProduct_name());
			psmt.setString(4, dto.getIdx());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 수정 중 예외 발생");
		}
		return result;
	}
	
	public int deletePost(String idx) {
		
		int result = 0;
		
		try {
			String query = "DELETE FROM myrecipe WHERE idx=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 삭제 중 예외 발생");
		}
		return result;
	}
}