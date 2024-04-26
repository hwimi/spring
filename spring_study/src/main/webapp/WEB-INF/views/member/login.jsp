<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<form action="/member/login" method="post">
<h1>로그인</h1>
 	<div class="mb-3">
  		<label for="i" class="form-label">ID</label>
  		<input type="text" class="form-control" name="id" id="i" placeholder="ID...">
	</div>
	<div class="mb-3">
  		<label for="p" class="form-label">Password</label>
  		<input type="password" class="form-control" name="pw" id="p" placeholder="PW...">
	</div>
	<button type="submit">로그인</button>
</form>
</div>