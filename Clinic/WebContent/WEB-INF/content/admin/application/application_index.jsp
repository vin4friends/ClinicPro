<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.application.index.title" /></h1>

<div class="internNav">
	<ul>
		<li>
			<s:a action="Application_create">
				<s:text name="admin.application.index.link.to_add" />
			</s:a>
		</li>
	</ul>
</div>
<div class="clearBoth"></div>
<div>
	<display:table 	id="list" 
					name="list" 
					requestURI="ApplicationIndex_list"
					pagesize="${pageSize}"
					class="groupedCol"
					excludedParams="action:ApplicationIndex_list nextMethod model.id">
		
		<s:set var="singular"><s:text name="entity.application.displaytag.singular" /></s:set>
		<s:set var="plural"><s:text name="entity.application.displaytag.plural" /></s:set>
		<display:setProperty name="paging.banner.item_name" value="${singular}" />
		<display:setProperty name="paging.banner.items_name" value="${plural}" />
		
		<s:set var="headerTitle"><span><s:text name="entity.application.application_key.short" /></span></s:set>
		<display:column title="${headerTitle}" class="firstCol" headerClass="firstCol">
			<div>
				<s:property value="%{#attr.get('list').applicationKey}" />
			</div>
		</display:column>

		<s:set var="headerTitle"><span><s:text name="entity.application.name" /></span></s:set>
		<display:column title="${headerTitle}" >
			<div>
				<s:property value="%{#attr.get('list').name}" />
			</div>
		</display:column>

			
		<s:set var="headerTitle"><span><s:text name="entity.application.type" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').type.messageKey}" />
			</div>
		</display:column>

		<s:set var="headerTitle"><span><s:text name="entity.application.allow_deselect.short" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').allowDeselect}" />
			</div>
		</display:column>
				
		<s:set var="headerTitle"><span><s:text name="entity.application.uom_distance.short" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').uomDistance.messageKey}" />
			</div>
		</display:column>
						
		<s:set var="headerTitle"><span><s:text name="entity.application.showMapLandingpage.short" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').showMapLandingpage.messageKey}" />
			</div>
		</display:column>
						
		<s:set var="headerTitle"><span><s:text name="entity.application.showMapDirections.short" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').showMapDirections.messageKey}" />
			</div>
		</display:column>
						
		<s:set var="headerTitle"><span><s:text name="entity.application.countries" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:iterator value="#attr.get('list').countries" var="country" status="status">
					<s:text name="%{#country.name}" /><s:if test="!#status.last">,<br /></s:if>
				</s:iterator>
			</div>
		</display:column>
						
		<s:set var="headerTitle"><span><s:text name="common.column.action" /></span></s:set>
		<display:column title="${headerTitle}">
			<s:a action="Application_edit" title="%{getText('admin.application.index.link.to_edit')}">
				<s:param name="model.id">${list.id}</s:param>
				<s:text name="common.edit" />
			</s:a>
			<s:a action="ApplicationDelete" title="%{getText('admin.application.index.link.to_delete')}">
				<s:param name="model.id">${list.id}</s:param>
				<s:text name="common.delete" />
			</s:a>
		</display:column>
	</display:table>
</div>