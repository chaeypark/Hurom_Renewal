package recipeUpload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;


public class UserfileDAO extends DBConnPool{
	public UserfileDAO() {
		super();
	}
	public int insertFile(UserfileDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO myrecipeimg (idx, oldname, newname, filetype, filenum) values (?,?,?,?,?)";
			
			psmt= con.prepareStatement(query);
			
			psmt.setString(1, dto.getIdx());
			psmt.setString(2, dto.getOldname());
			psmt.setString(3, dto.getNewname());
			psmt.setString(4, dto.getFiletype());
			psmt.setString(5, dto.getFilenum());
			
			result = psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("파일을 업로드 하던중 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<UserfileDTO> queryImage(String idx){
		List<UserfileDTO> imageList = new Vector<UserfileDTO>();
		
		String query = "SELECT * FROM myrecipeimg WHERE idx=? ORDER BY filenum DESC";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				UserfileDTO dto = new UserfileDTO();
				
				dto.setIdx(rs.getString("idx"));
				dto.setOldname(rs.getString("oldname"));
				dto.setNewname(rs.getString("newname"));
				dto.setFiletype(rs.getString("filetype"));
				dto.setFilenum(rs.getString("filenum"));
				
				imageList.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("이미지 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return imageList;
	}
	
	public int deletePost(String idx) {
		int result = 0;
		try {
		
			String query = "DELETE FROM myrecipeimg WHERE idx=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("이미지 파일 삭제 중 예외 발생");
		}
		return result;		
	}
}
