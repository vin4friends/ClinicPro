${parameters.after?if_exists}<#t/>
<#if parameters.inputcolspan?exists || parameters.labelcolspan?exists>
	</td><#lt/><#-- Write out the closing td for the html input -->
	<#include "/${parameters.templateDir}/lf_style/controlfooter-trlogic.ftl" />
<#else>
	<#if parameters.labelposition?default("top") == 'top'>
	</div> <#t/>
	<#else>
	</span> <#t/>
	</#if>
	</div>
</#if>
