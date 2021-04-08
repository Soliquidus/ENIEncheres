<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<meta charset="UTF-8">

<body>
	<div align ="center">
	<br>
	<br>
	<h2>Mon profil</h2>
	<br>
	<br>
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<ul class="list-group">
  <li class="list-group-item" ><p style = "font-weight: bold">Identifiant : </p><span class="badge badge-success" style="">${utilisateur.pseudo}</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">Nom : </p><span class="badge badge-success" style="">${utilisateur.nom }</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">Prénom : </p><span class="badge badge-success" style="">${utilisateur.prenom }</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">E-mail : </p><span class="badge badge-success" style="">${utilisateur.email}</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">Téléphone : </p><span class="badge badge-success" style="">${utilisateur.telephone}</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">Adresse : </p><span class="badge badge-success" style="">${utilisateur.rue}</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">Code postal : </p><span class="badge badge-success" style="">${utilisateur.codePostal}</span></li>
  <li class="list-group-item"><p style = "font-weight: bold">Ville : </p><span class="badge badge-success" style="">${utilisateur.ville}</span></li>
</ul>
		</div>
		<div class="col-4"></div>
	</div>
	<br>
		 <a class="btn btn-success" href="${pageContext.request.contextPath}/accueil"role="button">Retour à l'accueil</a>
		 <a class="btn btn-info"href= "${pageContext.request.contextPath}/updateUser"role="button">Modifier mon profil</a>
	</div>
	
		
	
</body>
<br>
<br>
<br>
<br>
<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>