<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="admin.location.export.title"><s:param value="country.name" /></s:text></h1>

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
<s:if test="!languages.empty">
	<s:form>
		<s:hidden name="country.countrycode" />
	
		<s:select 	list="languages" 
					name="selectedLanguage"
					key="admin.location.export.select.label.language" 
					value="selectedLanguage" />
	
		<s:submit key="admin.location.export.file.submit.button_text" action="LocationExport" method="export" />
	</s:form>
</s:if>
<s:else>
	<div class="nextStep noResult"><h2>There are no locations to export.</h2></div>
</s:else>