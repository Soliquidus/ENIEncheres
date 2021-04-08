<%@ page import ="fr.eni.encheres.messages.LecteurMessage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<meta charset="UTF-8">
<title>ENI-Enchéres - Création de compte</title>
<script> 
function validate()
{ 
     var conpassword= document.form.conpassword.value;
      if (password!=conpassword)
     { 
     alert("La confirmation du mot de passe doit être identique au mot de passe rentré"); 
     return false; 
     } 
}
</script>
</head>
<body>
<br>
<div align ="center">
		<h2>Créer mon profil</h2>
		<br>
		<br>
	<form name="form" action="${pageContext.request.contextPath }/newUser" method="post" onsubmit="return validate()">
    <div class="form-group col-md-3">
      <label for="inputPseudo">Identifiant :</label>
      <input type="text" class="form-control" name="pseudo"  value = "${utilisateur.pseudo}">
    </div>
    <br>
    <div class="form-row col-md-7">
    <div class="form-group col-md-6">
      <label for="inputNom">Nom :</label>
      <input class="form-control" type="text" name="nom"  value = "${utilisateur.nom}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputPrenom">Prénom  :</label>
      <input class="form-control" type="text" name="prenom" value = "${utilisateur.prenom}">
    </div>
  </div>
  <br>
  <div class="form-row col-md-7">
    <div class="form-group col-md-6">
      <label for="inputEmail4">E-mail :</label>
      <input class="form-control"  type="email" name="email" value = "${utilisateur.email}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputTelephone">Téléphone :</label>
      <input class="form-control" type="text" name="telephone" value = "${utilisateur.telephone}">
    </div>
  </div>
  <br>
  <div class="form-row col-md-7">
    <div class="form-group col-md-6">
      <label for="inputEmail4">Mot de passe :</label>
      <input class="form-control"  type="password" name="mot_de_passe" value = "${utilisateur.motDePasse}">
    </div>
    <div class="form-group col-md-6">
      <label for="inputTelephone">Confirmation de mot de passe :</label>
      <input class="form-control" type="password" name="conpassword" >
      <p><%=(request.getAttribute("errMessage") == null) ? ""
         : request.getAttribute("errMessage")%></p>
    </div>
  </div>
  <br>
  <div class="form-row col-md-7">
    <div class="form-group col-md-5">
      <label for="inputAdresse">Adresse :</label>
      <input  class="form-control" type="text" name="rue" value = "${utilisateur.rue}">
    </div>
    <div class="form-group col-md-5">
      <label for="inputCity">Ville :</label>
      <input class="form-control" type="text" name="ville" value = "${utilisateur.ville}">
    </div>
    <div class="form-group col-md-2">
      <label for="inputCodePostal">Code postal :</label>
      <input class="form-control" type="number" name="code_postal" value = "${utilisateur.codePostal}">
    </div>
  </div>
  <br>
  <button type="submit" class="btn btn-success">Valider</button>
</form>
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
	
</body>
<br>
<br>
<br>
<br>
<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>