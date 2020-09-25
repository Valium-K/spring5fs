<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%-- 하드코딩 -->
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--	<title>회원가입</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--	<h2>약관</h2>--%>
<%--	<p>약관 내용</p>--%>
<%--	<form action="step2" method="post">--%>
<%--	<label>--%>
<%--		<input type="checkbox" name="agree" value="true"> 약관 동의--%>
<%--	</label>--%>
<%--	<input type="submit" value="다음 단계" />--%>
<%--	</form>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><spring:message code="member.register"/> </title>
</head>
<body>
<h2><spring:message code="term"/> </h2>
<p><spring:message code="term.context"/> </p>
<form action="step2" method="post">
	<label>
		<input type="checkbox" name="agree" value="true"><spring:message code="term.agree"/>
	</label>
	<input type="submit" value="<spring:message code="next.btn"/>" />
</form>
</body>
</html>