package recipeboard;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import common.JSFunction;
import recipeUpload.FileUtil;
import recipeUpload.UserfileDAO;
import recipeUpload.UserfileDTO;


public class WritingController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if (!(session.getAttribute("memberid") == null)) {
			req.getRequestDispatcher("../pages/My_Recipe_Writing.jsp").forward(req, resp);

		}else {
			JSFunction.alertBack(resp, "로그인 하셔야 마이 레시피에 글 작성이 가능합니다.");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String saveDirectory = req.getServletContext().getRealPath("/Upload");		
				
		int maxPostSize = 1000000000;
		
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);

		if (mr == null) {
			JSFunction.alertLocation( resp, "첨부파일이 제한 용량을 초과합니다.","../recipe/write.do");
			return;
		}		
		Enumeration Pictures = mr.getFileNames();
		
		RecipeDTO dto = new RecipeDTO();
		RecipeDAO dao = new RecipeDAO();
		String idx = dao.maxIdx();
		
		Integer number = 1;
        try{
            number = Integer.valueOf(idx);
            number = number + 1;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        try {
        	idx = String.valueOf(number);
        }catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        
		dto.setIdx(idx);
		dto.setWriter_id(mr.getParameter("writer_id"));
		dto.setTitle(mr.getParameter("title"));
		dto.setContent(mr.getParameter("content"));
		dto.setProduct_name(mr.getParameter("productName"));
		String thumnail = "";
		
		if (Pictures != null) {
			int fileNum = 0;
			
			while(Pictures.hasMoreElements()) {
				fileNum++;
				UserfileDTO Fdto = new UserfileDTO();
				UserfileDAO Fdao = new UserfileDAO();
				String name = (String) Pictures.nextElement();
				String fileName = mr.getFilesystemName(name);
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				String ext = fileName.substring(fileName.lastIndexOf("."));
				String newFileName = now + ext;
				
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
				oldFile.renameTo(newFile);
				
				Fdto.setIdx(idx);
				Fdto.setOldname(fileName);
				Fdto.setNewname(newFileName);
				Fdto.setFiletype(ext);
				Fdto.setFilenum(String.valueOf(fileNum));
				Fdao.insertFile(Fdto);
				thumnail = newFileName;
				Fdao.close();
			}
			
				dto.setThumnail(thumnail);
				
				int result = dao.insertBoard(dto);
				dao.close();
				
				if (result == 1) {
					resp.sendRedirect("../recipe/main.do");
				}else {
					resp.sendRedirect("../recipe/write.do");
			}
		} else {
			int result = dao.insertBoard(dto);
			dao.close();
			
			if (result == 1) {
				resp.sendRedirect("../recipe/main.do");
			}else {
				resp.sendRedirect("../recipe/write.do");
			}
		}
	}
}