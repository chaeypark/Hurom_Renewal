package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	
	//  메시지 알림창을 표시한 후 특정 url로 이동하는 스크립트 코드
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = ""
						+ "<script>"
						+ "	alert('" + msg + "');"
						+ " location.href ='" + url + "';"
						+ "</script>";
			out.println(script);
		} catch (Exception e) {}
		
	}
	
	//메시지 알림창을 표시한 후 이전 페이지로 돌아가기
	public static void alertBack(String msg, JspWriter out){
		try {
			String script = ""
							+ "<script>"
							+ "  alert('" + msg + "');"
							+ "  history.back();"
							+ "</script>";
			out.println(script);
		} catch(Exception e) {}
	}
	
	//글쓰기 11. 서블릭에서 경고창 표시하고 다른 페이지로 이동
	//수정62-2. 
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			//글쓰기12. 서블릿에서 내용출력
			resp.setContentType("text/html;charset=UTF-8");
			
			//글쓰기13. getWriter()메서드를 통해 PrintWriter 객체얻어옴
			PrintWriter writer = resp.getWriter();
			
			//글쓰기14. 표현하고자 하는 스크립코드를 하나의 문자열로 만들어 서블릿에서 즉시 처리
			String script = "<script>"
						  +" 	alert('"+ msg +"');"
						  +" 	location.href='" + url + "';"
						  +"</script>";
			writer.print(script);
		
		}catch(Exception e) {
			
		}
	}
	
	
	
	//수정38. 서블릿에서 알림창 표시하고 이전 페이지로 돌아감
	public static void alertBack(HttpServletResponse resp, String msg) {
		
		try {
			//수정39. 서블릿에서 내용출력하기 위해 콘텐츠 타입 지정
			resp.setContentType("text/html;charset=UTF-8");
			
			//수정40. getWriter()메소드를 통해 PrintWriter 객체 얻어옴
			PrintWriter writer = resp.getWriter();
			
			//수정41. 표현하고자 하는 스크립트 코드를 하나의 문자열로 만들어 
			//서블릿에서 즉시 출력함 
			//history.back()
			String script = "<script>"
						  +" 	alert('"+ msg +"');"
						  +" 	history.back();" 
						  +"</script>";
			writer.print(script);
		
		}catch(Exception e) {
			
		}
	}
	
}









