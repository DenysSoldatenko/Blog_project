<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="comment" items="${comments }">
    <div class="media-object comment-item" data-id-comment="${comment.id}">
        <div class="media-object-section">
            <img src="${comment.account.avatarExists ? comment.account.avatar : comment.account.noAvatar }"
                 alt="${comment.account.name }" class="avatar"/>
        </div>
        <div class="media-object-section">
            <h5 class="name">${comment.account.name }</h5>
            <p>${comment.content }</p>
            <p class="meta">
                <small><fmt:formatDate type="both" value="${comment.created }"
                                       dateStyle="MEDIUM"
                                       timeStyle="SHORT"/></small> |
                <a href="javascript:reply('${comment.account.name }');">Reply</a>
            </p>
        </div>
    </div>
</c:forEach>