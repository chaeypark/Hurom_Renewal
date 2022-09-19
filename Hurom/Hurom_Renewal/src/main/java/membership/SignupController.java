package membership;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;

public class SignupController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/pages/SignUp.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		
		String member_id = req.getParameter("user_id");
		String member_pw = req.getParameter("user_pw");
		String member_name = req.getParameter("user_name");
		String email = req.getParameter("user_email");
		String year = req.getParameter("birthyear");
		String month = req.getParameter("birthmonth");
		String day = req.getParameter("birthday");
		String member_tel = req.getParameter("user_tel");
		String gender = req.getParameter("gender");
		dto.setMemberid(member_id);
		dto.setMemberpw(member_pw);
		dto.setMembername(member_name);
		dto.setEmail(email);
		dto.setBirthdate(new Date(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day)));
		dto.setMembertel(member_tel);
		dto.setGender(gender);
		dto.setRequired(req.getParameter("requiredList"));
		dto.setOptional(req.getParameter("optionalList"));
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.InsertMemberInfo(dto);
		
		if (result == 1) {
			JSFunction.alertLocation(resp, "가입이 완료되었습니다.", "../pages/index.jsp");
		}else {
			JSFunction.alertBack(resp, "중복된 아이디입니다.");
		}
		dao.close();

	}
}