<#if parameters.colspan??>
	<#assign columnSpan = parameters.colspan?default(2) />
	<td colspan="${columnSpan}"><#rt />
	<#if qTableLayout?? && qTableLayout.tablecolspan?? >
		<#assign columnCount = qTableLayout.currentColumnCount + columnSpan />	
		<#-- update the value of the qTableLayout.currentColumnCount bean on the value stack. -->
		${stack.setValue('#qTableLayout.currentColumnCount', columnCount)}
	</#if>
<#else>
	<#if parameters.labelposition?default("top") == 'top'>
	<div <#t/>
	<#else>
	<span <#t/>
	</#if>
	<#if parameters.id??>id="ctrl_${parameters.id}"<#rt/></#if> class="ctrl">
</#if>
<#include "/${parameters.templateDir}/simple/submit.ftl" />
<#include "/${parameters.templateDir}/simple/submit-close.ftl" />
<#if parameters.colspan??>
	</td><#lt/><#-- Write out the closing td for the html input -->
	<#include "/${parameters.templateDir}/lf_style/controlfooter-trlogic.ftl" />
<#else>
	<#if parameters.labelposition?default("top") == 'top'>
	</div> <#t/>
	<#else>
	</span> <#t/>
	</#if>
</#if>