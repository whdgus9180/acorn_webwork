<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>/guest/insertform.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class = "container">
	<form action="insert.jsp" method="post">
	<h1>방명록 작성 폼</h1>	
     	<div class="mb-2">
	     	<label for="name" class="form-label">작성자</label>
	        <input type="text" class="form-control" id="writer" name="writer"/>
        </div>
        <label for="content" class="form-label">내용</label><br>
        <textarea name="content" id="content" cols="50" rows="7">내용을 입력해 주세요.</textarea>
               
        <div class="mb-2">
	     	<label for="addr" class="form-label">비밀번호</label>
	        <input type ="text" class="form-control" id="pwd" name="pwd"/>
        </div>
        
        <button type="submit" class="btn btn-success">등록</button>    
         
    </div>
</body>
</html>