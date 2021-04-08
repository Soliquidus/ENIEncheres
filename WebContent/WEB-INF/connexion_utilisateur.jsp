<%@ page import ="fr.eni.encheres.messages.LecteurMessage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
<br>
<br>
<br>
<div class="container" align ="center">
	<form name="form" action="${pageContext.request.contextPath }/login" method="post">
	<h2>Me connecter</h2>
	<br>
  <div class="form-group col-md-5">
    <label for="pseudo">Identifiant :</label>
    <input type="text" class="form-control" name="pseudo" value="${utilisateur.pseudo}">
  </div>
  <div class="form-group col-md-5">
    <label for="mot_de_passe">Mot de passe :</label>
    <input type="password" class="form-control" name="mot_de_passe" value="${utilisateur.motDePasse }">
  </div>
  <br>
  <button type="submit" class="btn btn-success"   value = "connexion">Connexion</button>

	</form>
	<br>
	<br>
	<div id="creaCompte" align="center">
	<h2>Pas de compte ?</h2>
	<br>
	<br>
	<a class="btn btn-info"href= "${pageContext.request.contextPath}/newUser"role="button">Créer un compte</a>
	</div>
	
	<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
			  <strong>Erreur!</strong>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
</div>
</body>
<br>
<br>
<br>
<br>
<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>