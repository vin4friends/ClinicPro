<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><s:text name="admin.user.index.title" /></h1>

<div class="internNav">
	<ul>
		<li>
			<s:a action="User_create">
				<s:text name="admin.user.index.link.add" />
			</s:a>
		</li>
	</ul>
</div>
<div>
	<display:table 	id="list" 
					name="list" 
					requestURI="UserIndex"
					pagesize="${pageSize}"
					class="groupedCol" 
					excludedParams="action:UserIndex">
	
		<s:set var="singular"><s:text name="entity.user.displaytag.singular" /></s:set>
		<s:set var="plural"><s:text name="entity.user.displaytag.plural" /></s:set>
		<display:setProperty name="paging.banner.item_name" value="${singular}" />
		<display:setProperty name="paging.banner.items_name" value="${plural}" />
	
		<s:set var="headerTitle"><span><s:text name="entity.user.login" /></span></s:set>
		<display:column title="${headerTitle}" class="firstCol" headerClass="firstCol">
			<div>
				<s:property value="%{#attr.get('list').login}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.user.isLocked" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:text name="%{#attr.get('list').isLocked}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.user.firstname" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:property value="%{#attr.get('list').firstname}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.user.name" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:property value="%{#attr.get('list').name}" />
			</div>
		</display:column>
	
		<s:set var="headerTitle"><span><s:text name="entity.user.granted_authorities" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:iterator value="#attr.get('list').grantedAuthorities" var="role" status="status">
					<s:text name="%{#role.name}" /><s:if test="!#status.last">,<br /></s:if>
				</s:iterator>
			</div>
		</display:column>
		
		<s:set var="headerTitle"><span><s:text name="common.column.action" /></span></s:set>
		<display:column title="${headerTitle}">
			<div>
				<s:a action="User_edit" title="%{getText('admin.user.index.link.edit')}">
					<s:param name="model.id">${list.id}</s:param>
					<s:text name="common.edit" />
				</s:a>
				<s:a action="UserDelete" title="%{getText('admin.user.index.link.delete')}">
					<s:param name="model.id">${list.id}</s:param>
					<s:text name="common.delete" />
				</s:a>
				<s:if test="#attr.get('list').isLocked">
					<s:a action="User_unlock" title="%{getText('admin.user.index.link.unlock')}">
						<s:param name="model.id">${list.id}</s:param>
						<s:text name="admin.user.index.link.unlock.short" />
					</s:a>
				</s:if>
				<s:else>
					<s:a action="User_lock" title="%{getText('admin.user.index.link.lock')}">
						<s:param name="model.id">${list.id}</s:param>
						<s:text name="admin.user.index.link.lock.short" />
					</s:a>
				</s:else>
			</div>
		</display:column>
	</display:table>
</div>