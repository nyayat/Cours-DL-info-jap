<?php
//DAI Anna 21953144
//Question 4
?>

<?php
	require_once("verification.php");
	verification($login, $mdp);
	
	$article=$_REQUEST["article"];
	
	if(isset($_POST["text"])){
		$text=$_POST["text"];
		$position=array_key_last($_SESSION["commentaires"][$article])+1; 
		//récupère la dernière clé dans le tableau de commentaires
		//même si on unset la dernière clé, cela ne changera pas l'ordre de l'affichage des commentaires (question 5)
		$_SESSION["commentaires"][$article][$position]=$text;
		header("Location: lecture1.php?article=".$article);
		//dans le lien du header, on intègre directement l'identifiant de l'article pour faire plus simple
	}
	
?>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8" />
		<title>Commentaire</title>
	</head>
	
	<body>
		<form action="commentaire.php" method="post">
		<!--on utilise la méthode post parce que get ne semble pas approprié dans ce genre de cas (texte long) -->
			<textarea name="text" rows="10" cols="50">Saisissez votre commentaire.</textarea><br/>
			<button type="submit" name="article" value=<?php echo '"'.$article.'"'; ?> >Enregistrer</button>
		</form>
    </body>
</html>