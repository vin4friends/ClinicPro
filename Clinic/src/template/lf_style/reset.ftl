<#if  parameters.colspan?exists>
	<#include "/${parameters.templateDir}/lf_style/controlheader-trlogic.ftl" />
	<#assign columnSpan = parameters.colspan?default(2) />
	<td colspan="${columnSpan?html}">
</#if>
<div class="reset"><#t/>
<#include "/${parameters.templateDir}/simple/reset.ftl" />
</div><#t/>
<#include "/${parameters.templateDir}/lf_style/controlfooter.ftl" />
