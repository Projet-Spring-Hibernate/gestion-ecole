<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="row">
		<div>
			<a class="nav-link"
				href="${pageContext.request.contextPath}/bienvenue"><img
				id="logo_school" alt="logo"
				src="/01_gestion_ecoles/assets/images/online_education.png"></a>
		</div>
		<div>
			<h1 id="nomEcole">E-Poudlard - étudiant</h1>

		</div>
	</div>


	<nav class="navbar navbar-expand-lg navbar-light bg-light"
		style="background-color: #e3f2fd;">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/etudiants/affiche"">Mon
						compte</a></li>

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/enseignants/listeByEtudiant">Mes
						enseignants</a></li>

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/matieres/listeByEtudiant">Mes matières</a></li>

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/cours/listeByEtudiant">Mes cours</a></li>

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/etudiantCours/listeByEtudiant">Mes
						absences</a></li>


				<li class="nav-item" id="deconnexion"><a class="nav-link"
					href="<c:url value='/logout'/>">Se déconnecter</a></li>
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