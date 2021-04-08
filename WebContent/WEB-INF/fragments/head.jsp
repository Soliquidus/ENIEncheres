<%@ page import ="fr.eni.encheres.messages.LecteurMessage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Eni-Enchères</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<header>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
<a class="navbar-brand" href="${pageContext.request.contextPath}/accueil" style = "font-size: 35px"><img class="logo" src="${pageContext.request.contextPath}/Logo.png" width="150">
  Eni-Enchères</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  <c:if test="${sessionScope.utilisateur == null}">
    <ul class="navbar-nav ">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/connexion" style ="text-decoration: underline">S'incrire - Se connecter<span class="sr-only">(current)</span></a>
      </li>
      </ul>
    </c:if>
      <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#" style ="text-decoration: underline">Enchères en cours<span class="sr-only">(current)</span></a>
      </li>
      </ul>
     <c:if test="${sessionScope.utilisateur != null}">
      <ul class="navbar-nav mr-auto">
 		 <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/newArticle" style ="text-decoration: underline">Vendre un article<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/profile" style ="text-decoration: underline">Mon profil<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/logout" style ="text-decoration: underline">Déconnexion<span class="sr-only" >(current)</span></a>
      </li>
    </ul>
    </c:if>
  </div>
</nav>
</header>