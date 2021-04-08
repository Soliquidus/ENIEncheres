<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>

<%-- <c:out value="${articles_utilisateur}"></c:out>
<c:out value="Nom : ${utilisateur.nom }"></c:out>
<c:out value="ID : ${utilisateur.noUtilisateur }"></c:out> --%>
<br>
<br>
<br>	
<div class="row"  align = "center">
			<c:forEach items="${articles}" var="article">
				<div class="col-4">
				<div class="card" style="width: 18rem;">
				  <img src="${pageContext.request.contextPath}/noArticle.jpg" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">${article.nomArticle}</h5>
				    <p class="card-text">${article.description}</p>
				    
				  </div>
				  <ul class="list-group list-group-flush">
				    <li class="list-group-item">Prix : ${article.prixInitial } points</li>
				    <li class="list-group-item">Fin de l'enchère : ${article.dateFinEncheres}</li>
				    <li class="list-group-item">Vendeur : ${utilisateur.pseudo}</li>
				  </ul>
				  
				  <div class="card-body">
					<a href="#" class="btn btn-primary">Voir l'enchère</a>
				  </div>
				  
				</div>
			
		<%-- 	<c:out value="${article.photo }"></c:out> --%>
				</div>
		</c:forEach>
</div>

	<c:if test="${sessionScope.utilisateur != null}">
<div align = "center">		
	<h2 style ="text-decoration: underline">Mes articles en vente</h2>
</div>

<br>
<br>
	<div class="row"  align = "center">
			<c:forEach items="${articles_utilisateur}" var="article">
				<div class="col-4">
				<div class="card" style="width: 18rem;">
				  <img src="${pageContext.request.contextPath}/noArticle.jpg" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">${article.nomArticle}</h5>
				    <p class="card-text">${article.description}</p>
				    
				  </div>
				  <ul class="list-group list-group-flush">
				    <li class="list-group-item">Prix : ${article.prixInitial } points</li>
				    <li class="list-group-item">Fin de l'enchère : ${article.dateFinEncheres}</li>
				    <li class="list-group-item">Vendeur : ${utilisateur.pseudo}</li>
				  </ul>
				  
				  <div class="card-body">
					<a href="#" class="btn btn-primary">Voir l'enchère</a>
				  </div>
				  
				</div>
			
		<%-- 	<c:out value="${article.photo }"></c:out> --%>
				</div>
		</c:forEach>
	
	</div>
	
	</c:if>
	
	
	
	
	
	
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
<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>