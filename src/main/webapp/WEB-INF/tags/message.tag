<%@ tag import="com.example.project.services.I18nService" %>
<%@ tag import="com.example.project.services.impl.ServiceManager" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ attribute name="key" required="true" type="java.lang.String" %>
<%@ attribute name="args" required="false" type="java.lang.String" %>

<%
    I18nService i18nService = ServiceManager.getInstance(request.getServletContext()).getI18nService();
    String value;
    if (args != null) {
        String[] arguments = args.split(",");
        value = i18nService.getMessage(key, request.getLocale(), (Object[]) arguments);
    } else {
        value = i18nService.getMessage(key, request.getLocale());
    }
%>
<%=value %>