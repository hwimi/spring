<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<form action="/user/list">
<c:forEach items="${list }" var="uvo">
<%-- <div class="card-group di">
  <div class="card">
    <img src="/re/image/하늘.jpg" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">${uvo.nickName }</h5>
      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
      <p class="card-text"><small class="text-body-secondary">마지막 접속시간:${uvo.lastLogin }</small></p>
    </div>
  </div>
  </div> --%>
  <div class="row row-cols-1 row-cols-md-2 g-4">
  <div class="col">
    <div class="card">
      <img src="/re/image/하늘.jpg" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">${uvo.nickName }</h5>
        <p class="card-text">${uvo.email }</p>
         <p class="card-text">
         <c:forEach items="${uvo.authList }" var="authList">
         	${authList.auth }
         </c:forEach>
         </p>
        
         <p class="card-text"><small class="text-body-secondary">마지막 접속시간:${uvo.lastLogin }</small></p>        
     
      </div>
    </div>
  </div>
  </div>
</c:forEach>

</form>



<jsp:include page="../layout/footer.jsp"></jsp:include>