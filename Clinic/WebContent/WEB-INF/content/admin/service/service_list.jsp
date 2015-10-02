<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.serviceGroup.list.title"><s:param value="country.name" /></s:text></h1>

<s:component template="internNav">
    <s:param name="body">
		<li>
			<s:a action="LocationIndex">
				<s:param name="country.countrycode">${country.countrycode}</s:param>
				<s:text name="common.back_to_main_page" />
			</s:a>
		</li>
		<li>
			<s:a action="Location_create">
				<s:param name="country.countrycode">${country.countrycode}</s:param>
				<s:text name="admin.location.list.link.add" />
			</s:a>
		</li>
    </s:param>
</s:component>
<div id="search">
	<s:form>
		<s:hidden name="country.countrycode" />
		<s:textfield cssClass="searchInput" name="q" key="admin.location.list.search.label" labelposition="left" />
		<s:submit cssClass="searchBtn" key="admin.location.list.search.button.submit" action="LocationList" />
	</s:form>
</div>
<div style="clear: both;"></div>
<div>
	<display:table 	id="list" 
					name="list" 
					requestURI="ServiceGroupList"
					pagesize="${pageSize}"
					class="groupedCol"
					excludedParams="admin.location.list.search.button.submit action:LocationList nextMethod model.id">
		
		<s:set var="singular"><s:text name="entity.serviceGroup.displaytag.singular" /></s:set>
		<s:set var="plural"><s:text name="entity.serviceGroup.displaytag.plural" /></s:set>
		<display:setProperty name="paging.banner.item_name" value="${singular}" />
		<display:setProperty name="paging.banner.items_name" value="${plural}" />
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.code" /></span></s:set>
		<display:column title="${headerTitle}" class="firstCol" headerClass="firstCol">
			<div>
				<s:property value="%{#attr.get('list').code}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.name" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:property value="%{#attr.get('list').name}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.comments" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:property value="%{#attr.get('list').comments}" />
			</div>
		</display:column>
				
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.isActive" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:property value="%{#attr.get('list').isActive}" />
			</div>
		</display:column>
	</display:table>
</div>