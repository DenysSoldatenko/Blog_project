<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
    <tbody>
    <tr>
        <th style="width: 270px;">Categories</th>
    </tr>
    <c:forEach var="categoryEntry" items="${CATEGORY_MAP }">
        <c:set var="cat" value="${categoryEntry.value}"/>
        <tr>
            <td class="item">
                <a href="/news${cat.url }">${cat.name}
                    <span>(${cat.articles })</span></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>