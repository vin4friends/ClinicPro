<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
    
    <div id="footerLine">
        <ul id="footerCopy">
            <li><strong><span class="copyright">&copy;</span> <s:text name="common.copyright"/></strong></li>

            <s:action name="VersionSmall" namespace="/version" executeResult="true"/>
        </ul>
    <div class="clearFloat"></div>
    </div>