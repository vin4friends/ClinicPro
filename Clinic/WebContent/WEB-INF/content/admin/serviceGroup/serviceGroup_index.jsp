<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.serviceGroup.index.title" /></h1>

<div class="internNav">
	<ul>
		<li>
			<s:a action="ServiceGroup_create">
				<s:text name="admin.serviceGroup.index.link.add" />
			</s:a>
		</li>
	</ul>
</div>
<div>
       
	<display:table 	id="list" 
					name="list" 
					requestURI="ServiceGroupIndex"
					pagesize="${pageSize}"
					class="groupedCol" 
					excludedParams="action:ServiceGroupIndex">
	
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
				<s:text name="%{#attr.get('list').name}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.comments" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').comments}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.isActive" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isActive}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.createdBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdBy}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.createdTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdTime}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.updatedBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedBy}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.serviceGroup.updatedTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedTime}" />
			</div>
		</display:column>	
		
		<s:set var="headerTitle"><span><s:text name="common.column.action" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:a action="ServiceGroup_edit" title="%{getText('admin.serviceGroup.edit.title')}">
					<s:param name="model.id">${list.id}</s:param>
					<s:text name="common.edit" />
				</s:a>
				<s:a action="ServiceGroupDelete" title="%{getText('admin.serviceGroup.delete.title')}">
					<s:param name="model.id">${list.id}</s:param>
					<s:text name="common.delete" />
				</s:a>
			</div>
		</display:column>
	</display:table>
	
	<!-- 
	<div id="search">
	<s:form>
		<s:hidden name="country.countrycode" />
		<s:textfield cssClass="searchInput" name="q" key="admin.location.list.search.label" labelposition="left" />
		<s:submit cssClass="searchBtn" key="admin.location.list.search.button.submit" action="ServiceGroupList" />
	</s:form>
	 -->
</div>
</div>