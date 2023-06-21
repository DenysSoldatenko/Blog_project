document.addEventListener("DOMContentLoaded", function () {
    const comments = document.querySelectorAll(".commentlist li.comment");
    const loadMoreButton = document.getElementById("load-more-button");
    const loadMoreContainer = document.getElementById("load-more-container");
    let visibleComments = 5;
    const commentsPerLoad = 5;

    function updateCommentsVisibility() {
        for (let i = 0; i < comments.length; i++) {
            if (i < visibleComments) {
                comments[i].style.display = "block";
            } else {
                comments[i].style.display = "none";
            }
        }
    }

    loadMoreButton.addEventListener("click", function () {
        visibleComments += commentsPerLoad;
        updateCommentsVisibility();
        if (visibleComments >= comments.length) {
            loadMoreContainer.style.display = "none";
        }
    });

    updateCommentsVisibility();
    if (comments.length <= visibleComments) {
        loadMoreContainer.style.display = "none";
    }
});