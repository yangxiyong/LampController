<@master template="layout/master">
<h1>404 Page</h1>
    <#if exception??>
    <pre>${exception.message}</pre>
    <pre>${exception.stackTrace}</pre>
    </#if>
</@master>