package product_option;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class OptionDAO extends JDBConnect{
	public OptionDAO(ServletContext application){
		super(application);
	}
	public List<OptionDTO> Options(String product_num){
		List<OptionDTO> bbs = new Vector<OptionDTO>();
		
		String query = "SELECT * FROM PRODUCT_OPTION WHERE PRODUCT_NUM = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, product_num);
			rs = psmt.executeQuery();
			while(rs.next()) {
				OptionDTO dto = new OptionDTO();
				dto.setCount(rs.getInt("count"));
				dto.setOption_name(rs.getString("option_name"));
				dto.setPrice_change(rs.getInt("price_change"));
				
				bbs.add(dto);
			}
			
		}catch(Exception e) {
			System.out.println("옵션 찾는중 오류 발생");
			e.printStackTrace();
		}
		return bbs;
	}
}
