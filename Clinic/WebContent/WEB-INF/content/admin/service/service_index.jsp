<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.service.index.title" /></h1>

<div class="internNav">
	<ul>
		<li>
			<s:a action="ServiceMaster_create">
				<s:text name="admin.service.index.link.add" />
			</s:a>
		</li>
	</ul>
</div>
<div>
       
	<display:table 	id="list" 
					name="list" 
					requestURI="ServiceIndex"
					pagesize="${pageSize}"
					class="groupedCol" 
					excludedParams="action:ServiceIndex">
	
		<s:set var="singular"><s:text name="entity.service.displaytag.singular" /></s:set>
		<s:set var="plural"><s:text name="entity.service.displaytag.plural" /></s:set>
		<display:setProperty name="paging.banner.item_name" value="${singular}" />
		<display:setProperty name="paging.banner.items_name" value="${plural}" />
	
		<s:set var="headerTitle"><span><s:text name="entity.service.code" /></span></s:set>
		<display:column title="${headerTitle}" class="firstCol" headerClass="firstCol">
			<div>
				<s:property value="%{#attr.get('list').code}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.service.name" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').name}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.service.typeCode" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').typeCode}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.groupCode" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').groupCode}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.visitType" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').visitType}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isAppointable" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isAppointable}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.specialInstructions" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').specialInstructions}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isMultiple" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isMultiple}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isChargable" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isChargable}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.sellingPrice" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').sellingPrice}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.estimationTimeMins" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').estimationTimeMins}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isResourceDependent" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isResourceDependent}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isPackageService" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isPackageService}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isSurgicalService" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isSurgicalService}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isEquipmentRequired" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isEquipmentRequired}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isOTRequired" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isOTRequired}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isHighRated" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isHighRated}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isAuthorisationRequired" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isAuthorisationRequired}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.startDate" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').startDate}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.endDate" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').endDate}" />
			</div>
		</display:column>
		<s:set var="headerTitle"><span><s:text name="entity.service.cptCode" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').cptCode}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.activityType" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').activityType}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.isActive" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isActive}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.createdBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdBy}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.createdTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').createdTime}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.updatedBy" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedBy}" />
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="entity.service.updatedTime" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').updatedTime}" />
			</div>
		</display:column>	
		
		<s:set var="headerTitle"><span><s:text name="common.column.action" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:a action="ServiceMaster_edit" title="%{getText('admin.service.edit.title')}">
					<s:param name="model.id">${list.id}</s:param>
					<s:text name="common.edit" />
				</s:a>
				<s:a action="ServiceDelete" title="%{getText('admin.service.delete.title')}">
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