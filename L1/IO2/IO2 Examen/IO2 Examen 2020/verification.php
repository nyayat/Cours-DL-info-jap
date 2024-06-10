<?php
//DAI Anna 21953144
//Question 2
?>

<?php
	include_once("login.php");
	session_start();
	
	if(isset($_POST["login"])){
		$login=$_POST["login"];
	}
	else{
		$login=""; //par défaut, pour éviter de vérifier si la variable $login est définie ou non
	}
	
	if(isset($_POST["mdp"])){
		$mdp=$_POST["mdp"];
	}
	else{
		$mdp=""; //idem qu'avec $login
	}
	
	function verification($login, $mdp){
		if($login=="user0" && $mdp="user0" && !isset($_SESSION["login"])){ 
		//créer une nouvelle session si valide 
		/*comme dit précédemment, on n'inclut pas isset($login) et isset($mdp) dans la condition
		  car ces deux varibles sont définies par défaut*/
			$_SESSION["login"]=$login;
		}
		if(!isset($_SESSION["login"])){
		//retourne à la page de login si pas de session valide en cours
			page_login($login);
			exit;
		}
		//s'il y a déjà une session valide en cours, la fonction ne fait rien
	}
?>