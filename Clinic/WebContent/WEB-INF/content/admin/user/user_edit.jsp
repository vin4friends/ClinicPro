<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="currentMethod == 'add'">
	<h1><s:text name="admin.user.add.title" /></h1>
</s:if>
<s:else>
	<h1><s:text name="admin.user.edit.title" /></h1>
</s:else>


<s:component template="internNav">
    <s:param name="body">
		<li>
			<s:a action="UserIndex" onclick="return show_confirm();">
				<s:text name="common.back_to_main_page" />
			</s:a>
		</li>
    </s:param>
</s:component>
<s:form>

	<s:hidden name="currentMethod" />
	<s:hidden name="nextMethod" />
	<s:hidden name="model.id" />
	<s:hidden name="generatedPassword" />

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
			<s:textfield name="model.firstname" maxlength="255" key="entity.user.firstname" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:textfield name="model.name" maxlength="255" key="entity.user.name" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<tr><td colspan="3">&nbsp;</td></tr>
			<s:textfield name="model.login" maxlength="255" key="entity.user.login" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:textfield>
			<s:password name="password.password" maxlength="255" key="entity.user.password" required="%{currentMethod == 'add'}" showPassword="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" />
			</s:password>
			<s:submit key="common.generate_password.to_link" method="generatePassword">
				<s:param name="colspan" value="1" /> 
			</s:submit>
			<s:password name="password.passwordConfirmation" maxlength="255" key="entity.user.password.confirmation" required="%{currentMethod == 'add'}" showPassword="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:password>
			<s:if test="generatedPassword != null && !generatedPassword.empty">
				<tr>
					<td colspan="1" class="label"><span class="label"><s:text name="common.generate_password.result" /></span></td>
					<td colspan="2" class="ctrl"><span class="input"><s:property value="generatedPassword" /></span></td>
				</tr>
			</s:if>
			<tr><td colspan="3">&nbsp;</td></tr>
			<s:checkbox name="model.isLocked" key="entity.user.isLocked">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:checkbox>
			<s:radio	name="selectedRole"  
						key="entity.user.granted_authorities" 
						list="availableRoles" 
						listKey="id" 
						listValue="%{getText(name)}"
						value="selectedRole"
						required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" /> 
			</s:radio>
		    <s:select	list="availableCountries"
						name="selectedCountries"
						key="entity.user.countries"
						listKey="countrycode"
						listValue="name"
						value="selectedCountries"
						headerKey="null"
						headerValue="%{getText('admin.user.add_edit.no_country')}"
						multiple="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="2" />
			</s:select>
		</tbody>
	</table>
	<s:submit key="common.button.save" action="%{nextStep}" />
</s:form>