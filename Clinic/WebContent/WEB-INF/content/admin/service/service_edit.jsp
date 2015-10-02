<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="currentMethod == 'add'">
	<h1><s:text name="admin.service.add.title" /></h1>
</s:if>
<s:else>
	<h1><s:text name="admin.service.edit.title" /></h1>
</s:else>


<s:component template="internNav">
    <s:param name="body">
		<li>
			<s:a action="ServiceIndex" onclick="return show_confirm();">
				<s:text name="common.back_to_main_page" />
			</s:a>
		</li>
    </s:param>
</s:component>
<s:form>

	<s:hidden name="currentMethod" />
	<s:hidden name="nextMethod" />
	<s:hidden name="model.id" />
	
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
		
		
				
		<s:textfield name="model.code" maxlength="255" key="entity.service.code" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			
			<s:textfield name="model.name" maxlength="255" key="entity.service.name" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.typeCode" maxlength="255" key="entity.service.typeCode" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			
			
		<s:select list="serviceGroups"
		   	              name="serviceGroup.code"
		       	          key="entity.service.groupCode"
		           	      listKey="code"
		               	  listValue="name"
		               	  value="serviceGroup.code">
					<s:param name="labelcolspan" value="1" /> 
					<s:param name="inputcolspan" value="2" /> 
				</s:select>
			<s:textfield name="model.groupCode" maxlength="255" key="entity.service.groupCode" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.visitType" maxlength="255" key="entity.service.visitType" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.specialInstructions" maxlength="255" key="entity.service.specialInstructions">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.sellingPrice" maxlength="255" key="entity.service.sellingPrice" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.estimationTimeMins" maxlength="255" key="entity.service.estimationTimeMins" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.cptCode" maxlength="255" key="entity.service.cptCode">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.startDate" maxlength="255" key="entity.service.startDate" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.endDate" maxlength="255" key="entity.service.endDate">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.activityType" maxlength="255" key="entity.service.activityType">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			
			<s:checkbox name="model.isAppointable" key="entity.service.isAppointable">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isMultiple" key="entity.service.isMultiple">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isChargable" key="entity.service.isChargable">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isResourceDependent" key="entity.service.isResourceDependent">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isPackageService" key="entity.service.isPackageService">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isSurgicalService" key="entity.service.isSurgicalService">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isEquipmentRequired" key="entity.service.isEquipmentRequired">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isOTRequired" key="entity.service.isOTRequired">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isHighRated" key="entity.service.isHighRated">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isAuthorisationRequired" key="entity.service.isAuthorisationRequired">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:checkbox name="model.isActive" key="entity.service.isActive">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
		</tbody>
		</table>
	<s:submit key="common.button.save" action="%{nextStep}" />
</s:form>