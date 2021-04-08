<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Créer une nouvelle vente</title>
<body>
<div align = "center">
<br>
<br>
<div align ="center">
		<h2 style ="text-decoration: underline">Nouvelle vente</h2>
		<br>
		<br>
	<form name="form" action="${pageContext.request.contextPath }/newArticle" method="post" enctype="multipart/form-data">
    <div class="form-group col-md-3">
      <label for="inputPseudo">Article :</label>
      <input type="text" class="form-control" name="nom_article"  value = "${article.nomArticle}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputDescription">Description  :</label>
      <textarea class="form-control" name="description_article"  rows="3" >${article.description}</textarea>
    </div>
  </div>
  <br>
  <div class="form-group col-md-7">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Catégorie :</label>
      <select  name="no_categorie">
      <c:forEach items="${categories }" var="categorie">
      <option class="form-control"  value = "${categorie.getNoCategorie()}">${categorie.getLibelle() }</option>
      </c:forEach>
      </select>
    </div>
    <div class="form-group col-md-4">
      <label for="inputTelephone">Photo de l'article :</label>
      <input class="form-control" type="file" name="photo" size="50" value = "${article.photo}">
    </div>
    <div class="form-group col-md-4">
      <label for="inputTelephone">Mise à prix :</label>
      <input class="form-control" type="number" name="prix_initial" value = "${article.prixInitial}">
    </div>
  </div>
  <br>
  <div class="form-row col-md-7">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Date de l'enchère :</label>
      <input class="form-control"  type="date" name="date_debut" value = "${article.dateDebutEncheres}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputTelephone">Fin de l'enchère :</label>
      <input class="form-control" type="date" name="date_fin" value = "${article.dateFinEncheres}">
    </div>
  </div>
  <div style="display : none">
 	<label >no :</label>
 	<input type="text" name="no_utilisateur"  value ="${utilisateur.noUtilisateur}"/>
 	</div>
  <br>
  <button type="submit" class="btn btn-success">Valider</button>
  <button type="reset" class="btn btn-info">Réinitialiser</button>
  </form>
</div>
<%-- <c:forEach items="${categories}" var="categorie">
		<p>${categorie.getLibelle() }</p>
	</c:forEach> --%>
</body>
<br>
<br>
<br>
<br>
<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>