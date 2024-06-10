<?php
//DAI Anna 21953144
//Question 1
?>

<?php
function page_login($login){
	if($login!=null) echo "login et mot de passe non reconnus";	
	echo "<form action=\"home.php\" method=\"post\">
		<div>
			<label for=\"login\">Login </label>
			<input type=\"text\" name=\"login\" value=\"".$login."\"/><br/>
		</div>
		<div>
			<label for=\"mdp\">Mot de passe </label>
			<input type=\"password\" name=\"mdp\"/> <br/>
		</div>
		<button type=\"submit\">Se connecter</button>
	</form>";
}
?>