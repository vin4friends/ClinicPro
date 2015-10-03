<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.generalMaster.index.title" /></h1>

<div class="internNav">
	<ul>
		<li>
			<s:a action="GeneralMaster_create">
				<s:text name="admin.generalMaster.index.link.add" />
			</s:a>
		</li>
	</ul>
</div>
<div>
       
	<display:table 	id="list" 
					name="list" 
					requestURI="GeneralMasterIndex"
					pagesize="${pageSize}"
					class="groupedCol" 
					excludedParams="action:GeneralMasterIndex">
	
		<s:set var="singular"><s:text name="entity.generalMaster.displaytag.singular" /></s:set>
		<s:set var="plural"><s:text name="entity.generalMaster.displaytag.plural" /></s:set>
		<display:setProperty name="paging.banner.item_name" value="${singular}" />
		<display:setProperty name="paging.banner.items_name" value="${plural}" />
	
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.masterType" /></span></s:set>
		<display:column title="${headerTitle}" class="firstCol" headerClass="firstCol">
			<div>
				<s:property value="%{#attr.get('list').mastertype}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.name" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').name}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.isActive" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isActive}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.createdBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdby}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.createdTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdtime}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.updatedBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedby}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.generalMaster.updatedTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedtime}" />
			</div>
		</display:column>	
		
		<s:set var="headerTitle"><span><s:text name="common.column.action" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:a action="GeneralMaster_edit" title="%{getText('admin.generalMaster.edit.title')}">
					<s:param name="model.docid">${list.docid}</s:param>
					<s:text name="common.edit" />
				</s:a>
				<s:a action="GeneralMasterDelete" title="%{getText('admin.generalMaster.delete.title')}">
					<s:param name="model.docid">${list.docid}</s:param>
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
