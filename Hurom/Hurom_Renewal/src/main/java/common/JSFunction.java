package common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

public class JSFunction {
	//메시지 알림창을 표시한 후 특정 url로 이동하는 스크립트코드
	
	public static void alertLocation(String msg, String url, JspWriter out){
		try {
			String script = ""
					+ "<script>"
					+ " alert('" +msg + "');"
					+ " location.href = '" + url + "';"
					+ "</script>";
			out.print(script);
		} catch (Exception e) {}
	}
	
	//메시지 알림창을 표시한 후 이전 페이지로 돌아가기
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script =""
					+ "<script>"
					+ " alert('" +msg + "');"
					+ " history.back();"
					+ "</script>";
			out.print(script);
		}catch (Exception e) {}
	}
	
	//서블릿에서 경고창 표시하고 다른 페이지로 이동
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			//서블릿에서 내용을 출력하기위해 콘텐츠 타입 지정
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			
			String script = "<script>"
					+ "		alert('" + msg + "');"
					+ "		location.href='" + url + "';"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {
			System.out.println("alertLocation 문제");
			e.printStackTrace();
		}
	}
	
	//서블릿에서 알림창을 출력하고 이전 페이지로 돌아감
	
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			//서블릿에서 내용을 출력하기위해 콘텐츠 타입 지정
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			
			String script = "<script>"
					+ "		alert('" + msg + "');"
					+ "		history.back()"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {
			System.out.println("alertBack 문제");
			e.printStackTrace();
		}
	}
}
