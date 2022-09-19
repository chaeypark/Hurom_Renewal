package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	
	//새로운 쿠키 생성
	public static void makeCookie(HttpServletResponse response, String cName, String cValue, int cTime) {
		Cookie cookie  = new Cookie(cName, cValue); //쿠키 생성
		cookie.setPath("/"); //경로지정 (/는 전체에서 사용되는 쿠키를 생성함)
		cookie.setMaxAge(cTime); //유지 기간 설정
		response.addCookie(cookie);
	}
	
	//생성한 쿠키를 찾아 값을 반환하기
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue = "";//반환값
		
		//request 내장객체로부터 쿠키목록을 받아서 같은 이름의 쿠키를 반환함
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie c: cookies) {
				String cookieName = c.getName();
				if(cookieName.equals(cName)) {
					cookieValue = c.getValue();
				}
			}
		}
		return cookieValue;
	}
	
	//쿠키를 삭제하기
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);
		
	}
}








