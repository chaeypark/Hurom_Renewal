//a/s 및 부품신청 게시판 게시글 Edit Controller
package mvc2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

//수정20. edit서블릿 이동
@WebServlet("/mvc2/edit2.do") //view에 연결되어있음 
public class EditController2 extends HttpServlet {

	@Override //"접속했을 때" 라는 조건이므로 do get이 온다 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//수정21. 수정할 게시물의 일련번호 받아서
		String idx= req.getParameter("idx");
		//수정22. dao 생성
		ComponentBoardDAO dao = new ComponentBoardDAO();
		
		//수정 25. 기존 게시물의 내용을 담아 dto객체 가져오기
		ComponentBoardDTO dto = dao.selectView(idx);
		//수정26. request 영역에 저장하고
		req.setAttribute("dto", dto);
		
		//수정 27 포워드해줌
		//포워드할 때 보여져야 하는 대상 즉,Edit.jsp를 생성해줘야한다
		req.getRequestDispatcher("../pages/ComponentRequest_edit.jsp").forward(req, resp); 
	}
	
	//수정29. 내용 변경을 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		//수정30. 파일 업로드 처리
		//업로드 폴더의 물리적 경로를 확인
		String saveDirectory = req.getServletContext().getRealPath("/Upload");
		
		//수정32  초기화 매개변수로 설정한 첨부 파일의 최대 용량 확인 변수로 설정
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		//수정33. 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		//수정36. 파일 업로드 실패시
		if(mr == null) {
			//수정37. 안내창 표시
			JSFunction.alertBack(resp, "첨부 파일이 제한된 용량을 초과했습니다.");
			return;
		}
		//수정42. 파일 업로드 외 처리
		//업로드에 성공했다면 수정할 내용을 매개변수로 부터 가져옴
		String idx =  mr.getParameter("idx");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		//수정43. 비밀번호는 session에서 가져옴
		//비밀번호는 passController 서블릿에서 session에 저장한 값을 가져옴.
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		
		//수정44. 입력받은 값을 dto에 저장
		ComponentBoardDTO dto = new ComponentBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
		//수정45. 원본파일과 저장된 파일 이름을 설정
		String fileName = mr.getFilesystemName("ofile");
		
		//수정46. 파일명이 공백이 아니면 > 첨부파일이 있으면
		if(fileName != null) {
			
			//수정47. 새로운 파일명을 변경
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			//수정48 
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			//수정49 원래이름과 새로운 파일 이름을 dto에 저장
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
			//수정50 기존의 파일이 있는경우 삭제
			//prevSfile : Edit.jsp에서 전달받은 기존의 파일명
			FileUtil.deleteFile(req, "/Upload", prevSfile);
			
		}else {
			//수정46-1. 첨부파일이 없으면 기존파일의 이름을 유지.
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		//수정56. DB에 반영하기 위한 dao생성 
		ComponentBoardDAO dao = new ComponentBoardDAO();
		
		//수정61. updatePost()메서드의 실행결과 저장
		int result = dao.updatePost(dto);
		dao.close();
		
		//수정62. 수정에 성공하면 
		if(result == 1 ) {
			//수정63. session에 저장했던 비밀번호를 삭제
			session.removeAttribute("pass");
			//수정64. 상세보기 뷰로 이동하여 변경된 내용을 확인 // 끝!
			resp.sendRedirect("../mvc2/view2.do?idx=" + idx);
		}else {
			//수정62-1. 수정에 실패
			JSFunction.alertLocation(resp, "비밀번호를 확인하세요.", "../mvc2/view2.do?idx=" + idx );
		}
	}
}









