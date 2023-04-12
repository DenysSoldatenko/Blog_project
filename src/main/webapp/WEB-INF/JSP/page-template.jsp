<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Blog template</title>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/foundation.css">
    <link rel="stylesheet" type="text/css"
          href="http://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/app.css">
</head>
<body>
<header>
    <div class="callout primary" style="margin:0;">
        <div class="row">
            <div class="large-6 medium-6 columns">
                <h1>Blog name</h1>
            </div>
            <div class="large-6 medium-6 columns">
                <form action="#" method="get">
                    <div class="input-group" style="margin-top: 1em;">
                        <input id="search" class="input-group-field"
                               name="query" placeholder="Search query"
                               type="text">
                        <div class="input-group-button">
                            <input class="button" value="Find" type="submit">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</header>
<div class="row">
    <nav role="navigation" class="large-12 small-6 medium-8 columns">
        <ul class="breadcrumbs">
            <li>News</li>
        </ul>
    </nav>
    <div class="small-6 medium-4 columns">
        <ul id="mobile-category-menu"
            class="categories dropdown menu align-right hide-for-large"
            data-dropdown-menu>
            <li>
                <a href="javascript:void(0);">Categories</a>
                <ul class="menu" style="display:none;">
                    <li>
                        <a href="index.html">Elementum<span>(1)</span></a>
                    </li>
                    <li>
                        <a href="index.html">Proin<span>(2)</span></a>
                    </li>
                    <li>
                        <a href="index.html">Eleifend<span>(18)</span></a>
                    </li>
                    <li>
                        <a href="index.html">Phasellus<span>(24)</span></a>
                    </li>
                    <li>
                        <a href="index.html">Consectetuer<span>(33)</span></a>
                    </li>
                    <li>
                        <a href="index.html">Ultricies<span>(2)</span></a>
                    </li>
                    <li>
                        <a href="index.html">Mollis<span>(1)</span></a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<section class="row">
    <div id="mainContent" class="large-10 columns" style="min-height:600px;">
        <jsp:include page="${currentPage }"/>
    </div>
    <div class="columns large-2 show-for-large right" data-sticky-container>
        <div class="sticky categories show-for-large" data-sticky
             data-anchor="mainContent">
            <table>
                <tbody>
                <tr>
                    <th style="width:270px;">Categories</th>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Elementum<span>(1)</span></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Proin<span>(2)</span></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Eleifend<span>(18)</span></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Phasellus<span>(24)</span></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Consectetuer<span>(33)</span></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Ultricies<span>(2)</span></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="index.html">Mollis<span>(1)</span></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>
<footer class="footer">
    <div class="row">
        <div class="small-12 medium-6 large-5 columns">
            <p class="logo"><i class="fi-shield"></i>PERSONAL BLOG</p>
            <p class="footer-links">
                <a href="#">News</a>
                <a href="#">Search</a>
                <a href="about.html">About</a>
                <a href="contact.html">Contact</a>
            </p>
            <p class="copywrite">Copywrite not copywrite © 2015</p>
        </div>
        <div class="small-12 medium-6 large-4 columns">
            <ul class="contact">
                <li><p><i class="fi-marker"></i>1234 Spring Street New York, CT
                    00001</p></li>
                <li><p><i class="fi-telephone"></i><a href="tel:12223334444">+1-222-333-4444</a>
                </p></li>
                <li><p><i class="fi-mail"></i><a
                        href="mailto:contact@example.com">contact@example.com</a>
                </p></li>
            </ul>
        </div>
        <div class="small-12 medium-12 large-3 columns">
            <p class="about">About Blog</p>
            <p class="about subheader">A blog is a discussion or informational
                site published on the World Wide Web consisting of discrete
                entries ("posts") typically displayed in reverse chronological
                order. </p>
            <ul class="inline-list social no-bullet">
                <li><a href="#"><i class="fi-social-facebook"></i></a></li>
                <li><a href="#"><i class="fi-social-twitter"></i></a></li>
                <li><a href="#"><i class="fi-social-linkedin"></i></a></li>
                <li><a href="#"><i class="fi-social-github"></i></a></li>
            </ul>
        </div>
    </div>
</footer>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/what-input.js"></script>
<script src="/static/js/foundation.js"></script>
<script src="/static/js/app.js"></script>
</body>
</html>