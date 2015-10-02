<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
	
	<script type="text/javascript" src="${contextPath}/js/jquery-1.5.2.min.js"></script>

	<s:if test="googleMapsClientIdSet">
		<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=3.5&client=${googleMapsClientId}&sensor=false&language=en&region=GB" ></script>
	</s:if>
	<s:else>
		<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=3.5&sensor=false&sensor=false&language=en&region=GB" ></script>
	</s:else>
	
	<script type="text/javascript" src="${contextPath}/js/location_admin.js"></script> 
	<script type="text/javascript">

		var countrycode = "${country.countrycode}";
		var countryname = "${country.name}";
		
		var mapStartPosLat = 54.41;
		var mapStartPosLon = -4;
		var mapStartZoom = 4;
		
		
	</script>
</head>

<s:if test="currentMethod == 'add'">
	<h1><s:text name="admin.location.add.title" /></h1>
</s:if>
<s:else>
	<h1><s:text name="admin.location.edit.title" /></h1>
</s:else>

<s:component template="internNav">
    <s:param name="body">
        <li>
			<s:a action="LocationList" onclick="return show_confirm();">
				<s:param name="country.countrycode">${country.countrycode}</s:param>
				<s:text name="common.back_to_main_page" />
			</s:a>
        </li>
    </s:param>
</s:component>
<s:form>
	<s:hidden name="country.countrycode"/>
	<s:hidden name="nextMethod"/>
	<s:hidden name="model.id"/>

	<s:bean name="java.util.HashMap" id="qTableLayout">
		<s:param name="tablecolspan" value="2" />
	</s:bean>
	<table class="formTable" style="float: left; width: 60% !important; table-layout: fixed;">
		<colgroup>
			<col width="32%" />
			<col width="68%" />
		</colgroup>
		<tbody>
			<s:textfield name="model.sitecode" size="20" maxlength="50" key="entity.location.sitecode">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:radio 	name="selectedLocationType" 
						key="entity.location.locationtype" 
						list="locationTypes" 
						listKey="id"
						listValue="locationType" 
						value="selectedLocationType"
						required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:radio>
			<s:textfield name="model.sitename" size="20" maxlength="255" key="entity.location.sitename">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.tradingname" size="20" maxlength="255" key="entity.location.tradingname">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<tr><td colspan="2">&nbsp;</td></tr>
			<s:textfield name="model.street" size="20" maxlength="255" key="entity.location.street" id="street">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.district" size="20" maxlength="255" key="entity.location.district">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.town" size="20" maxlength="255" key="entity.location.town" id="town" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.county" size="20" maxlength="255" key="entity.location.county">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.postcode" size="20" maxlength="20" key="entity.location.postcode">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<tr><td colspan="2">&nbsp;</td></tr>
			<s:textfield name="model.telephone" size="20" maxlength="50" key="entity.location.telephone" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.telefax" size="20" maxlength="50" key="entity.location.telefax">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.email" size="20" maxlength="320" key="entity.location.email">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.url" size="20" maxlength="2000" key="entity.location.url">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.contact" size="20" maxlength="255" key="entity.location.contact">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<tr><td colspan="2">&nbsp;</td></tr>
			<s:textfield name="model.openweekday" size="20" maxlength="255" key="entity.location.openweekday" required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textfield name="model.openweekend" size="20" maxlength="255" key="entity.location.openweekend">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textfield>
			<s:textarea name="model.freeText" key="entity.location.freeText" cols="20" rows="5">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:textarea>
			<tr><td colspan="2">&nbsp;</td></tr>
			<%-- <s:textfield name="model.language" key="entity.location.language"/> --%>
			<s:checkboxlist name="selectedProductCategories"  
							key="entity.location.productcategories" 
							list="productCategories" 
							listKey="id" 
							listValue="name"
							value="selectedProductCategories"
							required="true">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:checkboxlist>
			<tr><td colspan="2">&nbsp;</td></tr>
			<s:checkbox name="model.isOnline" key="entity.location.isOnline">
				<s:param name="labelcolspan" value="1" /> 
				<s:param name="inputcolspan" value="1" /> 
			</s:checkbox>
		</tbody>
	</table>

	<div class="geoCoords" style="margin-left: 60%;">
		<s:textfield name="model.longitude" maxlength="20" key="entity.location.longitude" required="true" />
		<s:textfield name="model.latitude" maxlength="20" key="entity.location.latitude" required="true" />
		<div id="mapCanvas" style="margin-top: 0.5em; width: 300px; height: 300px; background-color: #DDDDDD; vertical-align: middle; text-align: center;"></div>
	</div>
	<div style="clear: both;"></div>
	<s:submit key="common.button.save" action="%{nextStep}" />
</s:form>
<div id="dummy" style="display:none"></div>
