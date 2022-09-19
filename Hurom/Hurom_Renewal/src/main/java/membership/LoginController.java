package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;

public class LoginController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String member_id = req.getParameter("user_id");
		String member_pw = req.getParameter("user_pw");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.MemberValidate(member_id, member_pw);
		
		if(dto.getMemberid() != null) {
			HttpSession session = req.getSession();//세션을 생성
			session.setAttribute("memberid", dto.getMemberid());//memberid라는 세션을 생성 후 로그인에 성공한 유저의 아이디를 넣음
			JSFunction.alertLocation(resp, dto.getMembername()+"님 환영합니다.", "../pages/index.jsp");
		}else {
			JSFunction.alertBack(resp, "아이디 혹은 비밀번호가 유효하지 않습니다.");
		}
		dao.close();
	}
}
