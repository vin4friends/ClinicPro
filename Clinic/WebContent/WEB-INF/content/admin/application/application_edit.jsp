<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>

<script type="text/javascript"
	src="${contextPath}/js/jquery-1.5.2.min.js"></script>

<s:if test="googleMapsClientIdSet">
	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?v=3.5&client=${googleMapsClientId}&sensor=false&language=en&region=GB"></script>
</s:if>
<s:else>
	<script type="text/javascript"
		src="http://maps.googleapis.com/maps/api/js?v=3.5&sensor=false&sensor=false&language=en&region=GB"></script>
</s:else>

<script type="text/javascript">
		
	var selectedImageSetValue="<s:property value="selectedImageSet"/>"; 
	var exampleUrl = "<s:property value="exampleUrl"/>";
	var urlInternetPattern = "<s:property value="urlInternetPattern"/>";
	var urlIntranetPattern = "<s:property value="urlIntranetPattern"/>";
	
	<s:if test="model.startLatitude!=null">
		var mapStartPosLat = <s:property value="model.startLatitude"/>;
	</s:if>
	<s:else>
		var mapStartPosLat = 54.41;
	</s:else>
	<s:if test="model.startLongitude!=null">
		var mapStartPosLon = <s:property value="model.startLongitude"/>;
	</s:if>
	<s:else>
		var mapStartPosLon = -4;
	</s:else>
	<s:if test="model.startZoom !=null">
		var mapStartZoom = <s:property value="model.startZoom"/>;
	</s:if>
	<s:else>
		var mapStartZoom = 4;	
	</s:else>
	
	var countries = {
			<s:iterator status="stat" value="availableCountries">
				"${countrycode}" : "${defaultLanguage}"<s:if test="!#stat.last">,</s:if>
	
			</s:iterator>
			};		
			
	
	var imageSets = [
		<s:iterator status="stat" value="availableImageSets">
			{"id" : "${id}" , "name" : "${name}"}<s:if test="!#stat.last">,</s:if>
		</s:iterator>
	]
	
	
	var cisia = {
			<s:iterator status="stat" value="cISIA">
				"${countryCode}" : {
				<s:iterator status="innerStat" value="iconAvailability">
					"${imageSetId}" : {
					"pcComplete" : ${pcIconsComplete},
					"pcEmpty" : ${pcIconsEmpty},	
					"ltComplete" : ${ltIconsComplete},					
					"ltEmpty" : ${ltIconsEmpty}					
					}<s:if test="!#innerStat.last">,</s:if>
				</s:iterator>
			}<s:if test="!#stat.last">,</s:if>
			</s:iterator>
	}
	
	var notCompleteText="<s:text name="admin.application.imageSet.notComplete"/>";
	
	</script>
<script type="text/javascript"
	src="${contextPath}/js/application_admin.js"></script>
</head>


<s:if test="currentMethod == 'add'">
	<h1>
		<s:text name="admin.application.add.title" />
	</h1>
</s:if>
<s:else>
	<h1>
		<s:text name="admin.application.edit.title" />
	</h1>
</s:else>

<s:component template="internNav">
	<s:param name="body">
		<li><s:a action="ApplicationIndex"
				onclick="return show_confirm();">
				<s:text name="common.back_to_main_page" />
			</s:a></li>
	</s:param>
</s:component>
<s:form>

	<s:hidden name="currentMethod" />
	<s:hidden name="nextMethod" />
	<s:hidden name="model.id" />

	<s:if test="currentMethod.equals('add')">
		<s:bean name="java.util.HashMap" id="qTableLayout">
			<s:param name="tablecolspan" value="3" />
		</s:bean>
	</s:if>
	<s:else>
		<s:bean name="java.util.HashMap" id="qTableLayout">
			<s:param name="tablecolspan" value="2" />
		</s:bean>
	</s:else>
	<table class="formTable" style="table-layout: fixed;">
		<s:if test="currentMethod.equals('add')">
			<colgroup>
				<col width="35%" />
				<col width="40%" />
				<col width="25%" />
			</colgroup>
		</s:if>
		<s:else>
			<colgroup>
				<col width="35%" />
				<col width="65%" />
			</colgroup>
		</s:else>

		<tbody>
			<s:textfield id="application_key" name="model.applicationKey"
				key="entity.application.application_key" maxlength="8"
				readonly="true" cssClass="mediumInput">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan" value="1" />
			</s:textfield>
			<s:if test="currentMethod == 'add'">
				<s:submit key="common.generate_new_key" method="generateKey">
					<s:param name="colspan" value="1" />
				</s:submit>
			</s:if>
			<s:textfield name="model.name" maxlength="255"
				key="entity.application.name" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<s:textarea name="model.description" cols="20" rows="5"
				key="entity.application.description">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textarea>
			<s:select list="availableCountries" name="selectedCountries"
				key="entity.application.countries" listKey="countrycode"
				listValue="name" value="selectedCountries" multiple="true"
				required="true" id="selectedCountries">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:select>


			<s:textfield name="model.cssUrl" maxlength="2000"
				key="entity.application.css_url" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<tr>
				<td colspan="%{currentMethod.equals('add') ? 3 : 2}">&nbsp;</td>
			</tr>
			<s:checkbox name="model.allowDeselect"
				key="entity.application.allow_deselect">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:checkbox>
			<s:radio name="model.type" key="entity.application.type"
				list="availableApplicationTypes" listValue="%{getText(messageKey)}"
				value="model.type" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:radio>




			<s:radio name="model.uomDistance"
				key="entity.application.uom_distance" list="availableDistanceUnits"
				listValue="%{getText(messageKey)}" value="model.uomDistance"
				required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:radio>
			<s:radio name="model.showMapLandingpage"
				key="entity.application.showMapLandingpage"
				list="availableMapvisibilities" listValue="%{getText(messageKey)}"
				value="model.showMapLandingpage" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:radio>
			<s:radio name="model.showMapDirections"
				key="entity.application.showMapDirections"
				list="availableMapvisibilities" listValue="%{getText(messageKey)}"
				value="model.showMapDirections" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:radio>
			<tr>
				<td colspan="%{currentMethod.equals('add') ? 3 : 2}">&nbsp;</td>
			</tr>
			<s:textfield name="model.searchInfoText"
				key="entity.application.search_info_text" maxlength="255">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<s:textfield name="model.maxLocationResult"
				key="entity.application.max_location_result" maxlength="2"
				cssClass="smallInput">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<s:radio list="availableImageSets" name="selectedImageSet"
				key="entity.application.imageSet" listKey="id" listValue="name"
				required="true" id="selectedImageSet">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />

			</s:radio>


			<s:if test="selectedCountries.size() > 0">
				<s:set var="exampleUrlStyle" value="''" />
			</s:if>
			<s:else>
				<s:set var="exampleUrlStyle" value="'display:none'" />

			</s:else>
			<tr>
				<s:if test="currentMethod.equals('add')">
					<td class="label" colspan="1"><span class="label"><s:text
								name="admin.application.add.label.iframe_link" />
					</span>
					</td>
					<td class="ctrl" colspan="2"><span id="exampleUrlSpan"
						class="input" style="${exampleUrlStyle}"><s:property
								value="exampleUrl" />
					</span>
					</td>
				</s:if>
				<s:else>
					<td class="label" colspan="1"><span class="label"><s:text
								name="admin.application.edit.label.iframe_link" />
					</span>
					</td>
					<td class="ctrl" colspan="1"><span id="exampleUrlSpan"
						class="input" style="${exampleUrlStyle}"><s:property
								value="exampleUrl" />
					</span>
					</td>
				</s:else>
			</tr>

			<s:textfield name="model.iframeId" maxlength="2000"
				key="entity.application.iframe_id" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>

			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
				<s:if test="currentMethod.equals('add')">
					<td colspan="3">
						<span class="label">
							<s:text name="admin.application.edit.label.helper"/>
						</span>
	
					</td>
				</s:if>
				<s:else>
					<td colspan="2">
						<span class="label">
							<s:text name="admin.application.edit.label.helper"/>
						</span>
	
					</td>
				</s:else>
			</tr>
			
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

			<tr>
					<td class="label" colspan="1">
						<span class="label"><s:text
								name="admin.application.edit.label.helper_template" />
						</span>
					</td>
					<td class="ctrl" colspan="2">
						<span class="input"><a href="../../js/helper.html.template"><s:text
								name="admin.application.edit.label.helper_template.download" /></a></span>
					</td>
			</tr>



			<tr>
				<s:if test="currentMethod.equals('add')">
					<td class="label" colspan="1"><span class="label"><s:text
								name="admin.application.edit.label.helper_js_link" />
					</span>
					</td>
					<td class="ctrl" colspan="2"><span id="helperJsUrlSpan"
						class="input" style="${exampleUrlStyle}"><s:property
								value="helperJsUrl" />
					</span>
					</td>
				</s:if>
				<s:else>
					<td class="label" colspan="1"><span class="label"><s:text
								name="admin.application.edit.label.helper_js_link" />
					</span>
					</td>
					<td class="ctrl" colspan="1"><span id="helperJsUrlSpan"
						class="input" style="${exampleUrlStyle}"><s:property
								value="helperJsUrl" />
					</span>
					</td>
				</s:else>
			</tr>



			<s:textfield name="model.helperUrl" maxlength="2000"
				key="entity.application.helper_url" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>




			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<s:textfield name="model.startLatitude"
				key="entity.application.start_latitude" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<s:textfield name="model.startLongitude"
				key="entity.application.start_longitude" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<s:textfield name="model.startZoom"
				key="entity.application.start_zoom" required="true">
				<s:param name="labelcolspan" value="1" />
				<s:param name="inputcolspan"
					value="%{currentMethod.equals('add') ? 2 : 1}" />
			</s:textfield>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="mapCanvas"
						style="margin: 10px 0 0 10px; width: 600px; height: 600px;"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>

		</tbody>
	</table>

	<s:submit key="common.button.save" action="%{nextStep}" />
</s:form>