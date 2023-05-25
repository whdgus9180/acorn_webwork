<%@page import="test.util.DbcpBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cPath = request.getContextPath();
	//DB 연동 가능한지 테스트
	new DbcpBean(); //객체 생성했을때 예외가 발생하지 않고 "Connection 얻어오기 성공!" 이 콘솔창에 출력되면된다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
	<div class ="container">
		<h1>인덱스 페이지입니다.</h1>
		<p>${pageContext.request.contextPath}</p>
		<ul><a href="${pageContext.request.contextPath}/guest/list.jsp" >방명록 목록보기</a></ul>
	</div>
</body>
</html>