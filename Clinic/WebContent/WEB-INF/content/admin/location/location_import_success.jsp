<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="admin.location.import.title"><s:param value="country.name"/></s:text></h1>

<p class="info"><s:text name="admin.location.import.success.info_text"><s:param value="country.name"/></s:text></p>

<table>
	<tr>
		<td><s:text name="admin.location.import.success.previous.label"/></td>
		<td><s:property value="uploadResult.oldLocationCount"/></td>
	</tr>
	<tr>
		<td><s:text name="admin.location.import.success.new.label"/></td>
		<td><s:property value="uploadResult.newLocationCount" /></td>
	</tr>
</table>

<s:component template="internNav">
    <s:param name="body">
		<li>
			<s:a action="LocationIndex">
				<s:text name="common.back_to_main_page" />
				<s:param name="country.countrycode">${country.countrycode}</s:param>
			</s:a>
		</li>
    </s:param>
</s:component>