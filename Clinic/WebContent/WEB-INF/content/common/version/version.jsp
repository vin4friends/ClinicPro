<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h2><s:text name="version.title" /></h2>
<p class="info"><s:text name="version.infoText" /></p>

<pre>
    <s:text name="version.continuousIntegrationProject" /><s:property value="%{' ' + versionProperties.continuousIntegrationProject}" />
    <s:text name="version.created" /><s:property value="%{' ' + versionProperties.created}" />
    <s:text name="version.release" /><s:property value="%{' ' + versionProperties.release}" />
    <s:text name="version.build" /><s:property value="%{' ' + versionProperties.build}" />
    <s:text name="version.propertyFile" /><s:property value="%{' ' + versionProperties.propertyFile}" />
    <br/>
    <s:text name="version.systemTime"/><s:date name="systemTime" format="%{getText('common.dateTimeSecondsPattern')}" />
    <s:text name="version.IP"/><s:property value="%{ipAddress}"/>
</pre>
