<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<h1>sampleList</h1>
	<div>
		<a href="${pageContext.request.contextPath}/sample/addSample">
		<button type="button">회원가입</button>
		</a>
	</div>

	<table class="table">
		<thead>
			<tr>
				<td>SAMPLE NO</td>
				<td>SAMPLE ID</td>
				<td>SAMPLE PW</td>
				<td>DELETE</td>
				<td>UPDATE</td>
			</tr>
		</thead>
		<tbody>
		<!--model안의 sampleList를 가져와서 사용  -->
		<c:forEach var="sample" items="${sampleList}">
			<tr>
				<td>${sample.sampleNo}</td>
				<td>${sample.sampleId}</td>
				<td>${sample.samplePw}</td> 
				<td><a href="/sample/removeSample?sampleNo=${sample.sampleNo}">DELETE</a></td>
				<td><a href="/sample/modifySample?sampleNo=${sample.sampleNo}">UPDATE</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="text-center">
		<ul class="pagination justify-content-center">
			<c:if test="${prevPage}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/sample/sampleList?currentPage=${(currentBlock - 1) * pagePerBlock}">< 이전</a></li>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<c:if test="${currentPage == i}">
					<li class="page-item"><a class="page-link" href="#">${i}</a></li>
				</c:if>
				<c:if test="${currentPage != i}">
					<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/sample/sampleList?currentPage=${i}">${i}</a></li>
				</c:if>
			</c:forEach>
			<c:if test="${nextPage}">
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/sample/sampleList?currentPage=${currentBlock * pagePerBlock + 1}">다음 ></a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>