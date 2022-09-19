package common;

import java.util.List;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";
		
		//전체 페이지 수 계산;
		int totalPages = (int)Math.ceil((double)totalCount/pageSize);
		
		//이전 페이지 블록 바로가기 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		if (pageTemp != 1) {
			pagingStr += "<a href='"+ reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='"+ reqUrl + "?pageNum=" +(pageTemp-1)+"'>[이전 페이지]</a>";
		}
		
		//각 페이지의 번호 출력
		int blockCount =1;
		while(blockCount<=blockPage && pageTemp <= totalPages){
			if (pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				pagingStr += "&nbsp;";
				pagingStr += "<a href='"+ reqUrl +"?pageNum=" +pageTemp+"'>"+pageTemp+"</a>";
				pagingStr += "&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		//다음 페이지 블록 바라고기 출력
		if (pageTemp <= totalPages) {
			pagingStr += "<a href='"+ reqUrl + "?pageNum=" +(pageTemp)+"'>[다음 페이지]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='"+ reqUrl + "?pageNum=" +totalPages + "'>[마지막 페이지]</a>";
		}
		
		return pagingStr;
	}
	
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl, List<Object> searchWord) {
		String pagingStr = "";
		
		//전체 페이지 수 계산;
		int totalPages = (int)Math.ceil((double)totalCount/pageSize);
		
		//이전 페이지 블록 바로가기 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		if (pageTemp != 1) {
			pagingStr += "<a href='"+ reqUrl + "?pageNum=1'";
					for (int i =0; i<searchWord.size(); i++) {
						pagingStr += "&p_cat="+searchWord.get(i);
					};
			pagingStr += ">[첫 페이지]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='"+ reqUrl + "?pageNum=" +(pageTemp-1);
					for (int i =0; i<searchWord.size(); i++) {
						pagingStr += "&p_cat="+searchWord.get(i);
					};
			pagingStr += "'>[이전 페이지]</a>";
		}
		
		//각 페이지의 번호 출력
		int blockCount =1;
		while(blockCount<=blockPage && pageTemp <= totalPages){
			if (pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			}else {
				pagingStr += "&nbsp;";
				pagingStr += "<a href='"+ reqUrl +"?pageNum=" +pageTemp;
					for (int i =0; i<searchWord.size(); i++) {
						pagingStr += "&p_cat="+searchWord.get(i);
					};	
				pagingStr +="'>"+pageTemp+"</a>";
				pagingStr += "&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		//다음 페이지 블록 바라고기 출력
		if (pageTemp <= totalPages) {
			pagingStr += "<a href='"+ reqUrl + "?pageNum=" +(pageTemp);
					for (int i =0; i<searchWord.size(); i++) {
						pagingStr += "&p_cat="+searchWord.get(i);
					};	
			pagingStr +="'>[다음 페이지]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='"+ reqUrl + "?pageNum=" +totalPages;
					for (int i =0; i<searchWord.size(); i++) {
						pagingStr += "&p_cat="+searchWord.get(i);
					};			
			pagingStr += "'>[마지막 페이지]</a>";
		}
		return pagingStr;
	}
}