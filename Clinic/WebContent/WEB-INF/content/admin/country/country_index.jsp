<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.country.index.title" /></h1>

<div class="internNav">
	<ul>
		<li>
			<s:a action="Country_create">
				<s:text name="admin.country.index.link.add" />
			</s:a>
		</li>
	</ul>
</div>
<div>
       
	<display:table 	id="list" 
					name="list" 
					requestURI="CountryIndex"
					pagesize="${pageSize}"
					class="groupedCol" 
					excludedParams="action:CountryIndex">
	
		<s:set var="singular"><s:text name="entity.country.displaytag.singular" /></s:set>
		<s:set var="plural"><s:text name="entity.country.displaytag.plural" /></s:set>
		<display:setProperty name="paging.banner.item_name" value="${singular}" />
		<display:setProperty name="paging.banner.items_name" value="${plural}" />
	
		<s:set var="headerTitle"><span><s:text name="entity.country.countryCode" /></span></s:set>
		<display:column title="${headerTitle}" class="firstCol" headerClass="firstCol">
			<div>
				<s:property value="%{#attr.get('list').countryCode}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.country.countryName" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').countryName}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.country.regionCode" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').regionCode}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.country.isActive" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isActive}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.country.createdBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdBy}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.country.createdTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdTime}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.country.updatedBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedBy}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.country.updatedTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedTime}" />
			</div>
		</display:column>	
		
		<s:set var="headerTitle"><span><s:text name="common.column.action" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:a action="Country_edit" title="%{getText('admin.country.edit.title')}">
					<s:param name="model.countryId">${list.countryId}</s:param>
					<s:text name="common.edit" />
				</s:a>
				<s:a action="CountryDelete" title="%{getText('admin.country.delete.title')}">
					<s:param name="model.countryId">${list.countryId}</s:param>
					<s:text name="common.delete" />
				</s:a>
			</div>
		</display:column>
	</display:table>
</div>