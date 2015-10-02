<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{versionProperties.showOnPages}">
    <li style="margin-left:30px;"><strong style="margin-top: 0;"><s:text name="version.small.release" /> <s:property value="%{versionProperties.release}" /></strong></li>
    <li style="margin-left:5px;"><strong style="margin-top: 0;"><s:text name="version.small.build" /> <s:property value="%{versionProperties.build}" /></strong></li>
    <li style="margin-left:5px;"><strong style="margin-top: 0;"><s:text name="version.small.systemTime"/> <s:date name="systemTime" format="%{getText('common.dateTimeSecondsPattern')}" /></strong></li>
    <li style="margin-left:5px;"><strong style="margin-top: 0;"><s:text name="version.small.IP"/> <s:property value="%{ipAddress}"/></strong></li>
</s:if>