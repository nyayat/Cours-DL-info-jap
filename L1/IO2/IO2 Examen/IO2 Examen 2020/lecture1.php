<?php
//DAI Anna 21953144
//Question 5
?>

<?php
	require_once("verification.php");
	verification($login, $mdp);
	
	$tab[0]="Dazai Osamu";
	$tab[1]="Hori Tatsuo";
	$tab[2]="Natsume Soseki";
	$tab[3]="Akutagawa Ryunosuke";
	$article=$_GET["article"];
	
	if(isset($_GET["supprimer"])){
		$supprimer=$_GET["supprimer"];
		foreach($supprimer as $key){
			unset($_SESSION["commentaires"][$article][$key]);
		}
	}
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
				//on ne tiendra pas compte de la case [0] pour l'affichage
				//cette case ne nous servant qu'à l'initialisation
				$_SESSION["commentaires"][$article][0]="";
			}
		?>
			
		<form action="lecture1.php" method="get">
			<?php			
				$array=array_keys($_SESSION["commentaires"][$article]);
				unset($array[0]);
				foreach($array as $j){
					if(isset($_SESSION["commentaires"][$article][$j])){
						echo"<p><input type=\"checkbox\" name=\"supprimer[]\" value=".'"'.$j."\">";
						echo $_SESSION["commentaires"][$article][$j]."</p>";
					}
				}
			?>
			<button type="submit" name="article" value= <?php echo '"'.$article.'"' ?> >Supprimer</button>
		</form>
		<form action="commentaire.php" method="get">
			<button type="submit" name="article" value=<?php echo '"'.$article.'"'; ?> >Ajouter un commentaire</button>
		</form>
    </body>
</html>