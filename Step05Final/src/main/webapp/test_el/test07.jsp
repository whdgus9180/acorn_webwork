<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
	<p>savedId 라는 키값으로 저장된 값 : ${cookie.savedId.value }</p>
	<p>savedId 라는 키값으로 저장된 값이 있는지 여부 : ${not empty cookie.savedId }</p>
</body>
</html>