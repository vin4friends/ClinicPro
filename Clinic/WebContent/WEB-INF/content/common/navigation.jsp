<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="mainNavTop"></div>
<sec:authorize access="hasRole('ROLE_LOCATION_ADMIN') and fullyAuthenticated">
	<div class="mainNavLink">
		<s:a namespace="/admin/location" action="LocationIndex" id="navigation_link_admin_location_index">
			<span><s:text name="navigation.admin.location.index.link" /></span>
		</s:a>
	</div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SUPER_ADMIN') and fullyAuthenticated">
	<div class="mainNavLink">
		<s:a namespace="/admin/application" action="ApplicationIndex" id="navigation_link_admin_application_index">
			<span><s:text name="navigation.admin.application.index.link" /></span>
		</s:a>
	</div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_SUPPORT_ADMIN') and fullyAuthenticated">
	<div class="mainNavLink">
		<s:a namespace="/admin/country" action="CountryIndex" id="navigation_link_admin_country_index">
			<span><s:text name="navigation.admin.country.index.link" /></span>
		</s:a>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SUPPORT_ADMIN') and fullyAuthenticated">
	<div class="mainNavLink">
		<s:a namespace="/admin/serviceGroup" action="ServiceGroupIndex" id="navigation_link_admin_service_group_index">
			<span><s:text name="navigation.admin.serviceGroup.index.link" /></span>
		</s:a>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SUPPORT_ADMIN') and fullyAuthenticated">
	<div class="mainNavLink">
		<s:a namespace="/admin/user" action="UserIndex" id="navigation_link_admin_user_index">
			<span><s:text name="navigation.admin.user.index.link" /></span>
		</s:a>
	</div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_SUPPORT_ADMIN') and fullyAuthenticated">
	<div class="mainNavLink">
		<s:a namespace="/admin/service" action="ServiceIndex" id="navigation_link_admin_service_index">
			<span><s:text name="navigation.admin.service.index.link" /></span>
		</s:a>
	</div>
</sec:authorize>

<sec:authorize access="fullyAuthenticated">
	<div class="mainNavLink">
		<a href="<s:url value="/logout" />"><s:text name="common.logout"/></a>
	</div>
</sec:authorize>
