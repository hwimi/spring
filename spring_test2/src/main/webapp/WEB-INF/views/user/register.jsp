<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<div  class="container-md">
<form action="/user/register" method="post">
<h1>회원가입</h1>
	<div class="mb-3">
  		<label for="e" class="form-label">E-mail</label>
  		<input type="email" class="form-control" name="email" id="e" placeholder="E-mail...">
	</div>
	<div class="mb-3">
  		<label for="p" class="form-label">Password</label>
  		<input type="password" class="form-control" name="pwd" id="p" placeholder="PW...">
	</div>
	<div class="mb-3">
  		<label for="n" class="form-label">Nick_Name</label>
  		<input type="text" class="form-control" name="nickName" id="n" placeholder="nickName..">
	</div>
	<button type="submit">join</button>
</form>
</div>

<script type="text/javascript" src="/re/js/userRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>