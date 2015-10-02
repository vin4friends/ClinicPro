<#include "/${parameters.templateDir}/lf_style/controlheader-core.ftl" />
<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
<#if parameters.inputcolspan??>
	<#assign inputColumnSpan = parameters.inputcolspan />
	<td <#t />
	<#if hasFieldErrors>
        class="errorCtrl" <#t/>
	<#else>
        class="ctrl" <#t/>
	</#if>
	colspan="${inputColumnSpan}"><#t />
	<#if qTableLayout?exists && qTableLayout.tablecolspan?exists >
		<#assign columnCount = qTableLayout.currentColumnCount + inputColumnSpan />	
		<#-- update the value of the qTableLayout.currentColumnCount bean on the value stack. -->
		${stack.setValue('#qTableLayout.currentColumnCount', columnCount)}
	</#if>
<#else>
	<#if parameters.labelposition?default("top") == 'top'>
	<div <#t/>
	<#else>
	<span <#t/>
	</#if>
	<#if parameters.id??>id="ctrl_${parameters.id}" <#rt/></#if> 
	<#t />
	<#if hasFieldErrors>
        class="errorCtrl"<#t/>
	<#else>
        class="ctrl"<#t/>
	</#if>
	>
</#if>
<#--
	Only show message if errors are available.
	This will be done if ActionSupport is used.
-->
<#if hasFieldErrors>
	<#list fieldErrors[parameters.name] as error>
		<p class="errorInfo"><span>${error?html}</span></p><#t/>
	</#list>
</#if>

