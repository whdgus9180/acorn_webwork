
<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. post 방식으로 전송되는 회원의 이름, 주소를 추출한다.
		request.setCharacterEncoding("utf-8");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		//추가할 회원 정보를 MemberDto 객체에 담는다
		GuestDto dto = new GuestDto();
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setPwd(pwd);
	
	//2. DB 에 저장한다.
		GuestDao dao = GuestDao.getInstance();
		boolean isSuccess=dao.insert(dto); // 작업의 성공여부가 리턴된다.
	
	//3. 응답한다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>/guest/insert.jsp</title>
</head>
<body>
	<div class="container">
		<h1>알림</h1>
		<p>
		<%if(isSuccess){ %>
			<strong><%=writer%></strong> 님의 정보가 저장되었습니다.
			<a href="list.jsp">확인</a>
		</p>
		<%}else{ %>
			<p>
				회원정보 저장 실패!
				<a href="insertform.jsp">다시작성</a>
			</p>
		<%} %>
	</div>
</body>
</html>
















