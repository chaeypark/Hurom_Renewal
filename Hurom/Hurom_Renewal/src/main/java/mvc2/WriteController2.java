package mvc2;
//a/s 및 부품신청 게시판 글쓰기 컨트롤러
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

public class WriteController2 extends HttpServlet{
	
	//글쓰기 2번 글쓰기 뷰로 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/pages/ComponentRequest.jsp").forward(req, resp);
	}
	
	//글쓰기4 dopost로 넘겨받은 폼값을 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//글쓰기5.
		//1. 파일 업로드 처리 
		//업로드 폴더의 물리적 경로를 확인 파일이 업로드 될 Uploads 폴더의 물리적인 경로를 확인
		String saveDirectory = req.getServletContext().getRealPath("/Upload");
		
		//web.xml에 컨텍스트 매개변수로 저장한 첨부파일의 최대용량 확인
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		//글쓰기6. 파일업로드를 위해 FileUtil.uploadFile() 메소드를 호출
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		
		//글쓰기9. 글쓰기8에서 리턴받은 결과가 공백이면 
		if(mr == null) {
			//글쓰기10 만약 파일 업로드 실패하면
			JSFunction.alertLocation(resp,"첨부 파일이 용량을 초과했습니다.", "../mvc2/writecomp.do");
			return;
		}
		
		//글쓰기 15. 파일 업로드 외 처리 > 일반적인 글쓰기와 동일
		//2. 파일 업로드 외 처리
		//객체생성해서 dto
		ComponentBoardDTO dto = new ComponentBoardDTO();
		dto.setName(mr.getParameter("name"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setPass(mr.getParameter("pass"));
		dto.setMemberid(mr.getParameter("memberid"));
		
		//글쓰기 16. 원본 파일명(ofile)과 저장된 파일명(sfile)설정
		String fileName = mr.getFilesystemName("ofile");
		
		//글쓰기 17. 첨부파일이 있으면 
		if(fileName != null) {
			
			//글쓰기18 파일명을 변경하기 위해 새로운 파일명 생성 
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			//글쓰기19 저장된 파일명 변경/ 원래파일명과 저장된 파일명을 따로 기록함
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile); //실제파일명이 변경되는 곳
			
			dto.setOfile(fileName); //원래이름 
			dto.setSfile(newFileName); //서버에 저장된 새로운 파일이름 
			
			//글쓰기20. DAO를 통해 DB에 작성된 내용을 저장
			ComponentBoardDAO dao = new ComponentBoardDAO();
			
			//글쓰기24 dao의 insertWrite()메서드 실행하여 결과값 받아오기
			int result = dao.insertWrite(dto);
			
			dao.close();
			
			//글쓰기25. 성공이냐 실패냐 >> 글쓰기 끝
			if(result == 1 ) { //성공이면 
				resp.sendRedirect("../mvc2/listAS.do"); //성공하면 목록으로 
			}else { //실패면 
				//글쓰기 실패시 다시 글쓰기 창으로 보내줌
				resp.sendRedirect("../mvc2/writecomp.do"); //글쓰기 창으로 
			}
			//첨부파일이 없을 때
		}else {
			//글쓰기20. DAO를 통해 DB에 작성된 내용을 저장
			ComponentBoardDAO dao = new ComponentBoardDAO();
			
			//글쓰기24 dao의 insertWrite()메서드 실행하여 결과값 받아오기
			int result = dao.insertWrite(dto);
			
			dao.close();
			
			//글쓰기25. 성공이냐 실패냐 >> 글쓰기 끝
			if(result == 1 ) { //성공이면 
				resp.sendRedirect("../mvc2/listAS.do"); //성공하면 목록으로 
			}else { //실패면 
				//글쓰기 실패시 다시 글쓰기 창으로 보내줌
				resp.sendRedirect("../mvc2/writecomp.do"); //글쓰기 창으로 
			}
		}
	}
}






