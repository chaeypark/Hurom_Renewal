package recipeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.ProductDTO;
import recipeUpload.UserfileDAO;
import recipeUpload.UserfileDTO;


public class RecipeViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RecipeDAO Tdao = new RecipeDAO();
		
		String idx = req.getParameter("idx");
		RecipeDTO Tdto = Tdao.selectView(idx);
		
		String product_name = Tdto.getProduct_name();
		
		ProductDTO pdto = Tdao.product_link(product_name);
		Tdao.close();
		
		Tdto.setContent(Tdto.getContent().replaceAll("\r\n", "<br>"));
		
		UserfileDAO Udao = new UserfileDAO();
		
		List<UserfileDTO> imgview  = Udao.queryImage(idx);
		
		Udao.close();

		req.setAttribute("Tdto", Tdto);
		req.setAttribute("pdto", pdto);
		req.setAttribute("imgview", imgview);
		
		req.getRequestDispatcher("/pages/My_Recipe_View.jsp").forward(req, resp);
	}
}
