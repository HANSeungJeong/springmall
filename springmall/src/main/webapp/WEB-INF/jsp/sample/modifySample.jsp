<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<h1>modifySample.jsp</h1>
	<form action="<%=request.getContextPath()%>/sample/modifySample" method="post">
	<input type="hidden" name="sampleNo" value="${sample.sampleNo}">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="sampleId" value="${sample.sampleId}" readonly></td>
		</tr>
		<tr>
			<td>PASSWORD</td>
			<td><input type="password" name="samplePw" value="${sample.samplePw}"></td>
		</tr>
	</table>
	<div>
		<a href="<%=request.getContextPath()%>/sample/sampleList">
		<button type="button">취소</button>
		</a>
		<input type="submit" value="회원수정">
	</div>
</body>
</html>