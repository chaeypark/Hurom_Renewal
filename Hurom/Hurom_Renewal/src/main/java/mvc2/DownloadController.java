package mvc2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileupload.FileUtil;

//다운로드2 서블릿 실행
//mapping대신 annotation사용
@WebServlet("/mvc2/download.do")
public class DownloadController extends HttpServlet {
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//다운로드3. 다운로드 링크를 클릭시 전달하는 매개변수 받아오기
		String ofile = req.getParameter("ofile");// 원본파일명
		String sfile = req.getParameter("sfile");//저장된 파일명
		String idx = req.getParameter("idx");//게시물의 일련번호
		
		//다운로드4 파일 다운로드 실행
		FileUtil.download(req, resp, "/Uploads", sfile, ofile);
		
		//다운로드13 dao생성
		QNABoardDAO dao = new QNABoardDAO();
		
		//다운로드14 다운로드 횟수를 증가 후 dao닫기 > 끝
		dao.downCountPlus(idx);
		
		dao.close();
	}
}
