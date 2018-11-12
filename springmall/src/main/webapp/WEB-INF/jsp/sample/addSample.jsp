<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<h1>addSample</h1>
	<form action="<%=request.getContextPath()%>/sample/addSample" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="text" name="sampleId"></td>
			<td>PASSWORD</td>
			<td><input type="password" name="samplePw"></td>
			<td>File</td>
			<td><input type="file" name="multipartFile"></td>
		</tr>
	</table>
	<div>
		<a href="<%=request.getContextPath()%>/sample/sampleList">
		<button type="button">취소</button>
		</a>
		<input type="submit" value="회원가입">
	</div>
	</form>
	</body>
</html>