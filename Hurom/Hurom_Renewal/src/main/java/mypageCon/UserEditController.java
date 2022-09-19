package mypageCon;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.JSFunction;
import membership.MemberDAO;
import membership.MemberDTO;


@WebServlet("/mypageCon/userEdit.do")
public class UserEditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("memberid");		

		MypageDAO dao = new MypageDAO();
		MemberDTO dto = dao.userEdit(id);		
		
		req.setAttribute("dto", dto);
		 
		if(session.getAttribute("memberid")!=null) {	// 로그인 유지			
			
			req.getRequestDispatcher("../mypage/userEdit.jsp").forward(req, resp);
			
		}else {	// 로그인 아님
			JSFunction.alertBack(resp, "유저 콘트 get 마이페이지는 로그인 후 사용가능합니다");			
		}
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
				
		if(session.getAttribute("memberid")!=null) {
			String id = (String)session.getAttribute("memberid");	
			
			String user_name = req.getParameter("user_name");
			String gender = req.getParameter("gender");
			
			String birthyear = req.getParameter("birthyear");
			String birthmonth = req.getParameter("birthmonth");
			String birthday = req.getParameter("birthday");	
						
			String optional = req.getParameter("optional");
			
			String user_email = req.getParameter("user_email");
			String user_tel = req.getParameter("user_tel");
			String passEdit = req.getParameter("passEdit");
			
			MemberDTO dto = new MemberDTO();

			dto.setMemberid(id);	
			
			dto.setMembername(user_name);	
			
			Date birthEdit = new Date(Integer.parseInt(birthyear)-1900, Integer.parseInt(birthmonth)-1, Integer.parseInt(birthday));				
			dto.setBirthdate(birthEdit);
			
			dto.setGender(gender);	
			dto.setMembertel(user_tel);			
			dto.setEmail(user_email);
			
			dto.setOptional(optional);
			
			
			
			
			MypageDAO dao = new MypageDAO();
			
			String boardPass = req.getParameter("boardPass");
			if(boardPass==null || boardPass=="") {
				JSFunction.alertBack(resp, "비밀번호를 입력해주세요");
				return;
			}
			
			boolean confirmed = dao.confirmPassword(boardPass, id);
			
			if(passEdit != null && passEdit != "") {
				dto.setMemberpw(passEdit);
			} else {
				dto.setMemberpw(boardPass);
			}
			 
			if(confirmed) { 
				int result = dao.updateUser(dto);
			  
				dao.close(); 
				//JSFunction.alertBack(resp, "수정되었습니다");
				JSFunction.alertLocation(resp, "수정되었습니다", "../mypageCon/mypage.do");	
			 
			} else { 				
				JSFunction.alertBack(resp, "비밀번호가 틀렸습니다"); 
				return; 
			 }			 
			
		} else {
			JSFunction.alertLocation(resp, "회원정보수정은 로그인 후 사용가능합니다", "../pages/index.jsp");	
		}
		
		
		
		
	}

	private Date formatter(String birthdate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
