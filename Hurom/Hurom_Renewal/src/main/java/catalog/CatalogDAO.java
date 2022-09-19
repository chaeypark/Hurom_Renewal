package catalog;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class CatalogDAO extends JDBConnect{
	public CatalogDAO(ServletContext application) {
		super(application);
	}
	public List getCatalog(String product_num){
		List a = new Vector();
		String query = "SELECT img_num FROM catalog_img where product_num = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, product_num);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				a.add(rs.getInt("IMG_NUM"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
}