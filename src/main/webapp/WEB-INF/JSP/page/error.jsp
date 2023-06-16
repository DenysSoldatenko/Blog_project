<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<div class="callout alert">
    <c:choose>
        <c:when test="${is404 && url != null }">
            <h6><tags:message key="message.error.resNotFound.withUrl" args="${url}"/></h6>
        </c:when>
        <c:when test="${is404}">
            <h6><tags:message key="message.error.resNotFound"/></h6>
        </c:when>
        <c:otherwise>
            <h6><tags:message key="message.error.tryLater"/></h6>
        </c:otherwise>
    </c:choose>
</div>