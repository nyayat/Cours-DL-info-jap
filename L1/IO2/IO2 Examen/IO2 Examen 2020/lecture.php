<?php
//DAI Anna 21953144
//Question 3
?>

<?php
	require_once("verification.php");
	verification($login, $mdp);
	
	$tab[0]="Dazai Osamu";
	$tab[1]="Hori Tatsuo";
	$tab[2]="Natsume Soseki";
	$tab[3]="Akutagawa Ryunosuke";
	$article=$_GET["article"];
?>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8" />
		<title>Lecture</title>
	</head>
	
	<body>
		<article><?php echo $tab[$article]; ?></article>
		
		<?php
			if(!(isset($_SESSION["commentaires"][$article]))){
				$_SESSION["commentaires"][$article][0]="";
				//on ne tiendra pas compte de la case [0] pour l'affichage
				//cette case ne nous servant qu'à l'initialisation
			}
			
			for($j=1; $j<count($_SESSION["commentaires"][$article]); $j++){
			//donc on démarre l'affichage à partir de la case [1]
				echo "<p>".$_SESSION["commentaires"][$article][$j]."</p>";
			}
		?>
		
		<form action="commentaire.php" method="get">
			<button type="submit" name="article" value=<?php echo '"'.$article.'"'; ?> >Ajouter un commentaire</button>
		</form>
    </body>
</html>