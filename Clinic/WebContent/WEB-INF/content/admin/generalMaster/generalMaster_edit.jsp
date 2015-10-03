<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="currentMethod == 'add'">
	<h1><s:text name="admin.generalMaster.add.title" /></h1>
</s:if>
<s:else>
	<h1><s:text name="admin.generalMaster.edit.title" /></h1>
</s:else>


<s:component template="internNav">
    <s:param name="body">
		<li>
			<s:a action="GeneralMasterIndex" onclick="return show_confirm();">
				<s:text name="common.back_to_main_page" />
			</s:a>
		</li>
    </s:param>
</s:component>
<s:form>

	<s:hidden name="currentMethod" />
	<s:hidden name="nextMethod" />
	<s:hidden name="model.docid" />
	
	<s:bean name="java.util.HashMap" id="qTableLayout">
		<s:param name="tablecolspan" value="3" />
	</s:bean>
	<table class="formTable" style="table-layout: fixed;">
		<colgroup>
			<col width="35%" />
			<col width="40%" />
			<col width="25%" />
		</colgroup>
		<tbody>
		
		
		
		<s:textfield name="model.mastertype" maxlength="255" key="entity.generalMaster.masterType" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			
			<s:textfield name="model.name" maxlength="255" key="entity.generalMaster.name" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			
			<s:checkbox name="model.isActive" key="entity.generalMaster.isActive">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
	<s:submit key="common.button.save" action="%{nextStep}" />
</s:form>