<?php
//DAI Anna 21953144
//Question 2 (bonus)
?>

<?php
	session_start();
	if(isset($_SESSION["login"])){
		$_SESSION=array();
		session_destroy();
	}
	header("Location: home.php");
?>