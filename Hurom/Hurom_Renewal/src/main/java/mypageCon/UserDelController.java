package mypageCon;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;
import membership.MemberDAO;
import membership.MemberDTO;

@WebServlet("/mypageCon/userDel.do")
public class UserDelController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("memberid");		

		MypageDAO dao = new MypageDAO();
		MemberDTO dto = dao.userEdit(id);			
		req.setAttribute("dto", dto);
		
		if(session.getAttribute("memberid")!=null) {	// 로그인 유지			
			req.setAttribute("dto", dto);
			
			req.getRequestDispatcher("../mypage/userDel.jsp").forward(req, resp);
			
		}else {	// 로그인 아님
			JSFunction.alertBack(resp, "회원탈퇴는 로그인 후 사용가능합니다");			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("memberid");
		
		String user_name = req.getParameter("user_name");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMemberid(id);
		dto.setMembername(user_name);
		
		MypageDAO dao = new MypageDAO();
		String boardPass = req.getParameter("boardPass");	
		
		if(boardPass==null || boardPass=="") {
			JSFunction.alertBack(resp, "비밀번호를 입력해주세요");
			return;
		}
		
		boolean confirmed = dao.confirmPassword(boardPass, id);		
		
		if(confirmed) {
			
			int result = dao.deleteUser(id);
			session.removeAttribute("memberid");
			
			dao.close();
			
			JSFunction.alertLocation(resp, "탈퇴 되었습니다", "../pages/index.jsp");
			
		} else {
			JSFunction.alertBack(resp, "비밀번호가 틀렸습니다");
			return;
		}
		
		
		
				
		
	}
	

}
