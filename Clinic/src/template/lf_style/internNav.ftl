<#if parameters.body?? && (parameters.body?html?length > 0)>
    <div class="internNav">
        <ul>
        ${parameters.body}
        </ul>
        <div class="clearFloat"></div>
    </div>
</#if>