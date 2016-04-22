<@master template="layout/master">
<h1>Error Page</h1>
    <#if exception??>
    <pre>${exception.message}</pre>
    <pre>${exception.stackTrace}</pre>
    </#if>
current url is ${requestContext.clientRequestedRelativeURLWithQueryString}
<#--${hello}-->
</@master>