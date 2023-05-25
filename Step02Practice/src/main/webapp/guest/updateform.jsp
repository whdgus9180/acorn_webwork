
<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   //1. 수정할 회원의 번호를 얻어낸다
	int num=Integer.parseInt(request.getParameter("num"));
	String pwd = request.getParameter("pwd");
   //2. 번호를 이용해서 DB 에 저장된 수정할 회원의 정보를 얻어낸다.
   GuestDto dto=GuestDao.getInstance().getData(num, pwd);
   //3. 수정할 양식을 클라이언트에게 응답한다.
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/updateform.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" />
</head>
<body>
   <div class="container">
      <h1>회원 정보 수정 양식</h1>
      <form action="update.jsp" method="post">
         <div class="mb-1">
            <label class="form-label" for="num">번호</label>
            <input class="form-control" type="text" id="num" name="num" value="<%=dto.getNum()%>"readonly/>
         </div>
         <div class="mb-1">
            <label class="form-label" for="writer">이름</label>
            <input class="form-control"  type="text" id="writer" name="writer" value="<%=dto.getWriter()%>"/>
         </div>
         <div class="mb-1">
            <label class="form-label" for="content">내용</label>
            <input class="form-control"  type="text" id="content" name="content" value="<%=dto.getContent()%>"/>
         </div>
         <div class="mb-1">
            <label class="form-label" for="pwd">비밀번호</label>
            <input class="form-control"  type="text" id="pwd" name="pwd" value="<%=dto.getPwd()%>"readonly/>
         </div>
         <div class="mb-1">
            <label class="form-label" for="regdate">주소</label>
            <input class="form-control"  type="text" id="regdate" name="regdate" value="<%=dto.getRegdate()%>"/>
         </div>
         <br>
         <button class="btn btn-outline-primary" type="submit">수정확인</button>
         <button class="btn btn-outline-warning" type="reset">취소</button>
      </form>
   </div>
</body>
</html>





