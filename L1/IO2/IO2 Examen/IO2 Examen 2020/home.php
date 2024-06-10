<?php
//DAI Anna 21953144
//Question 2
?>

<?php
	include_once("verification.php");
	verification($login, $mdp);
?>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8" />
		<title>Home</title>
	</head>
	
	<body>
		<!-- Toutes les vérifications sont faites, la suite du code (donc la page) s'affiche -->
		<h1>Choisissez un article</h1>
		
		<form action="lecture1.php" method="get">
			<select name="article" size="1">
				<option value="0">La Déchéance d'un homme</option>
				<option value="1">Le vent se lève</option>
				<option value="2">Je suis un Chat</option>
				<option value="3">Rashomon</option>
			</select>
			<button type="submit">Lire</button>
		</form>
		
		<footer>
			<a href="deconnexion.php">Déconnexion</a>
		</footer>
    </body>
</html>