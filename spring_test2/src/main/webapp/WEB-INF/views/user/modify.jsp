<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	    <sec:authentication property="principal.uvo.email" var="authEmail" />
        <sec:authentication property="principal.uvo.nickName" var="authNick" />
         <sec:authentication property="principal.uvo.regDate" var="regDate" />
         <sec:authentication property="principal.uvo.pwd" var="pwd" />
	<form action="/user/modify" method="post" enctype="multipart/form-data">
 	<div class="mb-3">
  		<label for="n" class="form-label">nick_name</label>
  		<input type="text" class="form-control" name="nickName" id="n" value="${authNick }"  placeholder="nickName...">
	</div>
	<div class="mb-3">
  		<label for="n" class="form-label">pwd</label>
  		<input type="text" class="form-control" name="pwd" id="p"   placeholder="pwd....">
	</div>
 	<div class="mb-3">
  		<label for="t" class="form-label">email</label>
  		<input type="text" class="form-control" name="email" id="e" value="${authEmail }" readonly="readonly" placeholder="email...">
	</div>
	<div class="mb-3">
  		<label for="t" class="form-label">regdate</label>
  		<input type="text" class="form-control" name="regdate" id="re" value="${regDate }" readonly="readonly" placeholder="regdate">
	</div>
	<button type="submit">수정</button>
	
	</form>
	<jsp:include page="../layout/footer.jsp"></jsp:include>