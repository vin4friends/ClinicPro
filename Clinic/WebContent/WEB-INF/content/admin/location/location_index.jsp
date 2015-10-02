<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h1><s:text name="admin.location.index.title" /></h1>

<div class="countrySelect">
	<s:form>
		<s:bean name="java.util.HashMap" id="qTableLayout">
			<s:param name="tablecolspan" value="3" />
		</s:bean>
		<table class="formTable">
			<tbody>
	        	<s:select list="countries"
		   	              name="country.countrycode"
		       	          key="admin.location.index.select.label.country"
		           	      listKey="countrycode"
		               	  listValue="name"
		               	  value="country.countrycode">
					<s:param name="labelcolspan" value="1" /> 
					<s:param name="inputcolspan" value="2" /> 
				</s:select>

				<tr><td colspan="3">&nbsp;</td></tr>

		        <s:submit key="admin.location.index.button.to_list" action="LocationList">
					<s:param name="colspan" value="1" />
				</s:submit>
		        <s:submit key="admin.location.index.button.to_import" action="LocationImport">
					<s:param name="colspan" value="1" />
				</s:submit>
		        <s:submit key="admin.location.index.button.to_export" action="LocationExport">
					<s:param name="colspan" value="1" />
				</s:submit>
			</tbody>
		</table>
	</s:form>
</div>