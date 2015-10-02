<#--
	This template does not support the label top position!!!

	This template handles: 
		* outputting a <td> for the label if there is one.

	Additionally it calls controlerheader-trlogic.ftl to handle table row logic.
-->
<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
<#if parameters.colspan?exists || parameters.inputcolspan?exists || parameters.labelcolspan?exists>
	<#include "/${parameters.templateDir}/lf_style/controlheader-trlogic.ftl" />
	<#assign labelColumnSpan = parameters.labelcolspan?default(1) />
<#else>
<div <#rt/><#if parameters.id??>id="grp_${parameters.id}"<#rt/></#if> class="grp">
</#if>
<#if parameters.label??>
	<#if parameters.labelcolspan??>
		<td <#t />
		<#if hasFieldErrors>
        	class="errorLabel" <#t/>
		<#else>
        	class="label" <#t/>
		</#if>
	 	colspan="${labelColumnSpan}"><#t/>
	</#if>
    <label <#t/>
	<#if parameters.id?exists>
        for="${parameters.id?html}" <#t/>
	</#if>
	<#if hasFieldErrors>
        class="errorLabel"<#t/>
	<#else>
        class="label"<#t/>
	</#if>
    ><#t/>
	<#if parameters.dynamicAttributes.icon?exists>
		<#if parameters.dynamicAttributes.iconTitle?exists>
        	<img src="${parameters.dynamicAttributes.icon?html}" title="${parameters.dynamicAttributes.iconTitle?html}" alt="${parameters.dynamicAttributes.iconTitle?html}"> <#t/>
       	<#else>
       		<img src="${parameters.dynamicAttributes.icon?html}" alt=""> <#t/>
       	</#if>
	</#if>
	<#if parameters.required?default(false) && parameters.requiredposition?default("right") != 'right'>
        <span class="required">*</span><#t/>
	</#if>
	<span class="labelText">${parameters.label?html}</span><#t/>
	<#if parameters.required?default(false) && parameters.requiredposition?default("right") == 'right'>
 		<span class="required">*</span><#t/>
	</#if>
	<#if !parameters.dynamicAttributes.noColon?exists>
	:<#t/>
	</#if>
	<#include "/${parameters.templateDir}/lf_style/tooltip.ftl" /> 
	</label><#t/>
	<#if parameters.labelcolspan?exists>
		</td><#lt/>
		<#-- We only update the qTableLayout.currentColumnCount if we actually printed out a td for the lable. -->
		<#if qTableLayout?exists && qTableLayout.tablecolspan?exists >
			<#assign columnCount = qTableLayout.currentColumnCount + labelColumnSpan />	
			<#-- update the value of the columnCounter.count bean on the value stack. -->
			${stack.setValue('#qTableLayout.currentColumnCount', columnCount)}
		</#if>
	</#if>
</#if>
