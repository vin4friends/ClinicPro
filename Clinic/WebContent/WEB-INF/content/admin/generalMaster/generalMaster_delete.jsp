<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1><s:text name="admin.generalMaster.delete.title" /></h1>

<s:component template="internNav">
    <s:param name="body">
		<li>
			<s:a action="GeneralMasterIndex">
				<s:text name="common.back_to_main_page" />
			</s:a>
		</li>
    </s:param>
</s:component>
<p><s:text name="admin.generalMaster.delete.question"><s:param value="model.code"/></s:text></p>
<s:form>
		<s:hidden name="nextMethod" />
		<s:hidden name="model.id" />
        <s:submit key="common.button.delete" action="%{nextStep}" />
</s:form>