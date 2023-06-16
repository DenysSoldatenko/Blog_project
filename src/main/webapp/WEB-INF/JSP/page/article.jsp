<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<div class="article thumbnail">
    <c:set var="category" value="${CATEGORY_MAP[article.idCategory] }"/>
    <img src="${article.logo }" alt="${article.title }"/>
    <div class="data">
        <%-- ----------------------------------------- Article content ----------------------------------------- --%>
        <h3>${article.title }</h3>
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
        <div class="content">${article.content }</div>
        <%-- ----------------------------------------- Social buttons ----------------------------------------- --%>
        <!-- AddToAny BEGIN -->
        <div class="a2a_kit a2a_kit_size_32 a2a_default_style">
            <a class="a2a_dd" href="https://www.addtoany.com/share"></a>
            <a class="a2a_button_facebook"></a>
            <a class="a2a_button_twitter"></a>
            <a class="a2a_button_telegram"></a>
            <a class="a2a_button_linkedin"></a>
            <a class="a2a_button_x"></a>
            <a class="a2a_button_snapchat"></a>
            <a class="a2a_button_reddit"></a>
            <a class="a2a_button_copy_link"></a>
        </div>
        <script async src="https://static.addtoany.com/menu/page.js"></script>
        <!-- AddToAny END -->
        <br>
        <%-- ----------------------------------------- Comments section ----------------------------------------- --%>
        <div class="comments">
            <jsp:include page="../fragment/new-comment.jsp"/>
            <div id="comments-list-container"
                 data-comments-count="${article.comments }"
                 data-id-article="${article.id }">
                <jsp:include page="../fragment/comments.jsp"/>
            </div>
            <div id="comments-load-more-ctrl" class="row column text-center">
                <a href="javascript:moreComments();"
                   class="button hollow expanded load-more-btn"
                ${article.comments >  fn:length(comments) ? '' : 'style="display:none"' }>Load More</a>
                <img src="/static/img/loading.gif" alt="Loading..."
                     class="loading-indicator"/>
            </div>
        </div>
    </div>
</div>