<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title><s:text name="common.page.title"/></title>
        <meta name="language" content="" />
        <meta name="keywords" content="" />
        <meta name="description" content="" />  
        <meta name="author" content="T-Systems MMS 2011" />     
        <meta name="copyright" content="" />                       
		<link href="${contextPath}/styles/locationfinder.css" rel="stylesheet" type="text/css" media="screen, print" />
    </head>

    <body>
    	<div id="wrapper">
    		<div id="header">
	        	<page:applyDecorator name="panel" page="/WEB-INF/content/common/header_public.jsp" />
	        </div>
            <decorator:body />  
            <div id="footer">
            	<page:applyDecorator name="panel" page="/WEB-INF/content/common/footer.jsp" />
           	</div>   
        </div>     
    </body>
</html>