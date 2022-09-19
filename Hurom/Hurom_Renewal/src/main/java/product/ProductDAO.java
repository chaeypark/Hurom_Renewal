package product;


import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;


public class ProductDAO extends JDBConnect{
	public ProductDAO(ServletContext application) {
		super(application);
	}
	
	public ProductDTO productView(String num) {
		ProductDTO dto = new ProductDTO();
		String query = "SELECT * FROM product WHERE product_num = ?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			while(rs.next()){
				dto.setProduct_num(rs.getString("product_num"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setP_cat(rs.getString("p_cat"));
			}
		}catch(Exception e) {
			System.out.println("selectView 만들던중 이상 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	//같은 카테고리에 속한 제품들 나타내주는 쿼리 ;
	public List<ProductDTO>  totalCount(String P_CAT) {
		List<ProductDTO> bbs= new Vector<ProductDTO>();
		
		String query = "SELECT * FROM Product WHERE P_CAT = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, P_CAT);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_num(rs.getString("product_num"));
				
				bbs.add(dto);
			}
		} catch(Exception e) {
			System.out.println("카테고리를 계산하는 중 예외가 발생되었음");
			e.printStackTrace();
		}
		return bbs;
	}
	
	
	public List<ProductDTO>  totalProduct(List<Object> catagories, Map<String, Object> map) {
		List<ProductDTO> bbs= new Vector<ProductDTO>();
		
		String query = "SELECT * FROM ( SELECT tb.*, ROWNUM rNum FROM ( SELECT * FROM PRODUCT ";
		
		if (catagories.size() != 0) {
			query += " WHERE";
			for (int i = 0; i< catagories.size(); i++) {
				while(i < catagories.size()-1) {
					query += " P_CAT = '" + catagories.get(i) + "' ||";
				}
				query += " P_CAT = '" + catagories.get(i) + "'";
			}
		}
		query += ") tb ) WHERE rNum BETWEEN ? AND ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProduct_num(rs.getString(1));
				dto.setProduct_name(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setP_cat(rs.getString(4));
				
				bbs.add(dto);
			}
		}catch(Exception e) {
			System.out.println("상품을 정렬하던 중 에러가 발생");
			e.printStackTrace();
		}
		return bbs;

	}
	public List<ProductDTO>  totalProduct() {
		List<ProductDTO> bbs= new Vector<ProductDTO>();
		
		String query = "SELECT * FROM Product";	
		try {
			System.out.println(query);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProduct_num(rs.getString(1));
				dto.setProduct_name(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setP_cat(rs.getString(4));
				
				bbs.add(dto);
			}
		}catch(Exception e) {
			System.out.println("상품을 정렬하던 중 에러가 발생");
			e.printStackTrace();
		}
		return bbs;

	}
	
	public int totalproductNumber(List<Object> catagories) {
		int result = 0;
		
		String query = "SELECT COUNT(*) FROM PRODUCT";
		
		if (catagories.size() != 0) {
			query += " WHERE";
			for (int i = 0; i< catagories.size(); i++) {
				while(i < catagories.size()-1) {
					query += " P_CAT = '" + catagories.get(i) + "' ||";
				}
				query += " P_CAT = '" + catagories.get(i) + "'";
			}
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			result = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("상품을 정렬하던 중 에러가 발생");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	

}