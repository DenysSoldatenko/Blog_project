$(document).foundation();
$(document).ready(function () {
    $('#mobile-category-menu ul.menu').removeAttr('style');
});

function moreComments() {
    var offset = $('#comments-list-container .comment-item').length;
    var idArticle = $('#comments-list-container').attr('data-id-article');
    $('#comments-load-more-ctrl .load-more-btn').css('display', 'none');
    $('#comments-load-more-ctrl .loading-indicator').css('display', 'block');
    $.ajax({
        url: '/ajax/comments?offset=' + offset + '&idArticle=' + idArticle,
        success: function (data) {
            $('#comments-load-more-ctrl .loading-indicator').css('display', 'none');
            $('#comments-list-container').append(data);
            var actualTotal = $('#comments-list-container .comment-item').length;
            var expectedTotal = $('#comments-list-container').attr('data-comments-count');
            if (actualTotal == expectedTotal) {
                $('#comments-load-more-ctrl .load-more-btn').css('display', 'none');
            } else {
                $('#comments-load-more-ctrl .load-more-btn').css('display', 'block');
            }
        },
        error: function (data) {
            alert(messages.errorAjax);
        }
    });
}

// ------------------------ Google plus integration ------------------------
var googleProfile = null;

function submitComment() {
    if (googleProfile == null) {
        $('#sigin-form').foundation('open');
    } else {
        //TODO submit new comment logic
    }
}

function onSignIn(googleUser) {
    googleProfile = googleUser.getBasicProfile();
    googleProfile.authToken = googleUser.getAuthResponse().id_token;
    $('#sigin-form').foundation('close');
    if (googleProfile.getImageUrl() != null) {
        $('#new-comment-container img').attr('src', googleProfile.getImageUrl());
    }
    $('#new-comment-container img').attr('alt', googleProfile.getName());
    $('#new-comment-container a.logout').css('display', 'block');
}

function gpLogout() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut();
    googleProfile = null;
    $('#new-comment-container a.logout').css('display', 'none');
    $('#new-comment-container img').attr('src', '/static/img/no_avatar.png');
    $('#new-comment-container img').attr('alt', messages.anonym);
}