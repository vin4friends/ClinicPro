<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

	
<div id="loginForm">
	<h1><s:text name="com.clinic.login.title" /></h1>
    <s:actionmessage />
    <s:fielderror />
    <s:actionerror />
     <form action="<s:url value='/login' />" method="post">
		<table id="loginTable">
			<tbody>
				<tr class="labelUser">
					<td><label for="j_username"><s:text name="com.clinic.login.username" /></label></td>
					<td><input type="text" id="j_username" name="j_username" value="<s:property value='%{#session.SPRING_SECURITY_LAST_USERNAME}' escape="false" />" maxlength="20" /></td>
				</tr>
				<tr class="labelPassword">
					<td><label for="j_password"><s:text name="com.clinic.login.password" /></label></td>
					<td><input type="password" id="j_password" name="j_password" maxlength="20" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" class="submitButton" value="<s:text name="com.clinic.login.action.login" />" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
