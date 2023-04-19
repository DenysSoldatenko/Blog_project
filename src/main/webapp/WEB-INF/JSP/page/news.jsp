<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="article" items="${list }">
    <c:set var="category" value="${CATEGORY_MAP[article.idCategory] }"/>
    <div class="article thumbnail">
        <a href="${article.articleLink }"><img src="${article.logo }" alt="${article.title }"/></a>
        <div class="data">
            <h3><a href="${article.articleLink }">${article.title }</a></h3>
            <ul class="vertical large-horizontal menu">
                <li><i class="fi-folder"></i><a
                        href="/news${category.url}">${category.name }</a></li>
                <li><i class="fi-comments"></i><fmt:formatNumber
                        value="${article.comments }"/> comments
                </li>
                <li><i class="fi-clock"></i><fmt:formatDate
                        value="${article.created }" dateStyle="FULL"
                        timeStyle="SHORT" type="both"/></li>
                <li><i class="fi-eye"></i>Hits: <fmt:formatNumber
                        value="${article.views }"/></li>
            </ul>
            <hr/>
            <div class="desc">${article.desc }</div>
        </div>
    </div>
</c:forEach>

<ul class="pagination text-center" role="navigation" aria-label="Pagination">
    <li class="pagination-previous"><a href="#"
                                       aria-label="Previous">Previous</a></li>
    <li><a href="#" aria-label="Page 1">1</a></li>
    <li class="ellipsis"></li>
    <li><a href="#" aria-label="Page 13">13</a></li>
    <li><a href="#" aria-label="Page 14">14</a></li>
    <li class="current">15</li>
    <li><a href="#" aria-label="Page 16">16</a></li>
    <li><a href="#" aria-label="Page 17">17</a></li>
    <li class="ellipsis"></li>
    <li><a href="#" aria-label="Page 43">43</a></li>
    <li class="pagination-next"><a href="#" aria-label="Next page">Next</a></li>
</ul>