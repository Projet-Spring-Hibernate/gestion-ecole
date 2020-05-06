
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header>
	<div class="row">
		<div>
			<a class="nav-link" href="${pageContext.request.contextPath}/bienvenue"><img id="logo_school" alt="logo"
				src="/01_gestion_ecoles/assets/images/online_education.png"></a>
		</div>
		<div>
			<h1 id="nomEcole">E-Poudlard - administrateur</h1>
		</div>
				<tr>
			<td><a href="?lang=en_US">English</a>|</td>
			<td><a href="?lang=fr_FR">French</a></td>
		</tr>
		
	</div>


	<nav class="navbar navbar-expand-lg navbar-light bg-light"
		style="background-color: #e3f2fd;">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
<!-- 			<ul class="navbar-nav"> -->

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/administrateurs/listeAll">Administrateurs</a> --%>
<!-- 				</li> -->
				

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiants/listeAll">Etudiants</a> --%>
<!-- 				</li> -->

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/enseignants/listeAll">Enseignants</a> --%>
<!-- 				</li> -->

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/promotions/listeAll">Promotions</a> --%>
<!-- 				</li> -->

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/matieres/listeAll">Matières</a></li> --%>

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cours/listeAll">Cours</a></li> --%>

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiantCours/listeAll">Absences</a></li> --%>

<%-- 				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/aides/listeAll">Aides</a></li> --%>

<!-- 				<li class="nav-item" id="deconnexion"><a class="nav-link" -->
<%-- 					href="<c:url value='/logout'/>">Se déconnecter</a></li> --%>
<!-- 			</ul> -->
						<ul class="navbar-nav">

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/administrateurs/listeAll"><spring:message code="label.administrators"/></a>
				</li>
				

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiants/listeAll"><spring:message code="label.students"/></a>
				</li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/enseignants/listeAll"><spring:message code="label.teachers"/></a>
				</li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/promotions/listeAll"><spring:message code="label.classes"/></a>
				</li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/matieres/listeAll"><spring:message code="label.subjects"/></a></li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cours/listeAll"><spring:message code="label.lessons"/></a></li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiantCours/listeAll"><spring:message code="label.absences"/></a></li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/aides/listeAll"><spring:message code="label.help"/></a></li>

				<li class="nav-item" id="deconnexion"><a class="nav-link"
					href="<c:url value='/logout'/>"><spring:message code="label.logout"/></a></li>
			</ul>
		</div>
	</nav>
</header>

<div id="aideBox">
	<h2>Aide</h2>
	
	${aide_contenu}

</div>

<div id="messagebox">
		<c:if test="${ not empty message}">
		<br/>
			<c:if test='${reussiteOperation.equals("true")}'>
				<div class="alert alert-success" role="alert">
  					${message}
				</div>
			</c:if>
			<c:if test='${reussiteOperation.equals("false")}'>
				<div class="alert alert-danger" role="alert">
	  				${message}
				</div>
			</c:if>
		</c:if>
</div>