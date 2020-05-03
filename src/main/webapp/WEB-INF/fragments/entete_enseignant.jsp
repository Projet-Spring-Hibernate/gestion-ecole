<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<div class="row">
		<div>
			<img id="logo_school" alt="logo"
				src="/01_gestion_ecoles/assets/images/online_education.png">
		</div>
		<div>
			<h1 id="nomEcole">WebCole - enseignant </h1>
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

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/enseignants/affiche">Mon compte</a>
				</li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiants/listeByEnseignant">Mes étudiants</a>
				</li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/promotions/listeByEnseignant">Mes promotions</a>
				</li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/matieres/listeByEnseignant">Mes Matières</a>
				</li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cours/listeByEnseignant">Cours</a></li>

				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/etudiantCours/listeByEnseignant">Absences</a></li>

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