<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pastry Gallery | Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>

<header class="mb-4">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">
            <img src="https://img.icons8.com/cotton/64/null/croissant--v1.png"/>
            <a class="navbar-brand" href="#">Pastry Gallery</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                </ul>
                <form class="d-flex" role="search" action="${pageContext.request.contextPath}/pastry-list" method="get">
                    <input name="searchName" class="form-control me-2" type="search" placeholder="Search"
                           aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<div class="container pastries">

    <div class="row">
        <div class="col-md-7 order-md-2 content-pastry">
            <h2 class="fw-normal pastry-heading lh-1 w-100">Operation finished</h2>
            <p class="lead description">Database initialized.</p>
            <p><a class="btn btn-secondary" href="/pastry-list">Go Home »</a></p>
        </div>
        <div class="col-md-5">
            <img src="https://img.icons8.com/wired/500/null/database.png"
                 class="rounded-start img-fluid">
        </div>
    </div>

    <hr class="item-divider">
</div>

<footer class="container-fluid bg-dark text-white p-3">
    <p class="float-end"><a href="#">Back to top</a></p>
    <p>© 2023 Pastry Gallery · <a href="#">Terms of use </a> · </p>
</footer>

</body>
</html>
