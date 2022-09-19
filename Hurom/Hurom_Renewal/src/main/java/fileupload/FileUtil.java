package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {

	//글쓰기7. 파일 업로드 처리
	//수정 34
	public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
		
		//처리를 위한 try-catch문
		try {
			//글쓰기8. 파일을 업로드하여 결과값 리턴함.
			//수정35.
			//MultipartRequest: 파일 업로드용. multipart/form-data 요청을 처리해주는 유틸리티 클래스 >글쓰기6으로 돌아가 처리됨
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
			
		}catch(Exception e) {
			System.out.println("파일 업로드 중 에러발생");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	//다운로드5. 선탣한 파일을 찾아 다운로드 실행함.
	public static void download(HttpServletRequest req,HttpServletResponse resp, String directory, String sfileName, String ofileName){
		//다운로드6. 서블릿에서 물리적 경로를 가져옴
		String sDirectory = req.getServletContext().getRealPath(directory);
		
		try {
			
			
			//다운로드7. 다운받고자 하는 파일을 찾아 입력스트림 생성 
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			//다운로드8. 한글 파일경우 깨짐 방지 
			//User-Agent 를 통해 클라이언트의 웹 브라우저 종류를 알아온 뒤
			String client = req.getHeader("User-Agent");
			
			//-1이란 말은 없단말임 >> 디코딩과정
			//인터넷 익스플로어의 경우와 아닐 때를 구분하여 문자열 디코딩을 처리함
			if (client.indexOf("WOW64") == -1){
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			}else {
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			//다운로드9. 파일 다운로드용 응답헤더 설정
			resp.reset(); //응답헤더 초기화 
			
			
			//다운로드 창을 띄우기위해 콘텐츠 타입 지정
			//8비트 단위의 바이너리 데이터를 의미
			resp.setContentType("application/octet-stream"); 
			
			//웹브라우저에 다운로드 창이 뜰 때 원본 파일명을 기본으로 입력되도록 설정
			resp.setHeader("Content-Disposition", "attachment; filename =\"" + ofileName + "\"");
			
			// jsp에서는 다운로드를 위해 또 다른 jsp파일을 열게되면
			//출력스트림이 중복으로 생성되어 예외가 발생하지만
			//서블릿에서는 발생하지 않음.
			//out.clear();
			
			//다운로드10. 새로운 출력 스트림 생성 
			OutputStream oStream =  resp.getOutputStream();
			
			//다운로드11. 읽어온 내용을 출력 스트링을 통해 파일 내용 출력함
			byte b[]= new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = iStream.read(b))>0) {
				oStream.write(b, 0, readBuffer);
			}
			
			//다운로드12. 입/출력 닫기 >> 여기까지의 과정을 다운로드4에서 처리함
			iStream.close();
			oStream.close();
					
		}catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("예외가 발생했습니다.");
			
		}
	}
	
	//삭제29 지정된 위치의 파일을 삭제
	//지우기만하고 보내줘야하는 부분은 없어서 리퀘스트만
	public static void deleteFile(HttpServletRequest req, String directory, String filename ) {
		//삭제30. 삭제하고자 하는 파일이 저장된 위치의 물리적 경로를 얻어옴
		//수정52
		String sDirectory = req.getServletContext().getRealPath(directory);
		
		//삭제31. 경로와 파일명을 결합하여 파일 객체를 생성
		//수정53
		File file = new File(sDirectory + File.separator + filename);
		
		//삭제32. 해당 경로에 파일이 존재하면 33. 삭제
		//수정54
		if(file.exists()) {
			//삭제33.
			//수정55.
			file.delete();
		}
	}
}









