<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="admin.location.import.title"><s:param value="country.name"/></s:text></h1>

<s:actionerror />

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
<s:form method="post" enctype="multipart/form-data">
	<s:hidden name="country.countrycode" />
	<s:file name="upload" key="admin.location.import.file.label" />
	<s:submit key="admin.location.import.file.submit.button_text" action="LocationImport" method="upload" />
</s:form>

