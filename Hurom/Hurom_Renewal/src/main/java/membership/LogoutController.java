package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/membership/logout.do")
public class LogoutController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		session.removeAttribute("memberid");//생성된 session값을 삭제함으로 로그아웃 기능을 수행
		
		req.getRequestDispatcher("/pages/index.jsp").forward(req, resp);
	}
}
