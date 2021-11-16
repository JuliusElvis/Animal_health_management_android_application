<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['rating']) && isset($_POST['review'])) {
	if ($db->dbConnect()) {
		$db->appRating("app_rating", $_POST['username'], $_POST['rating'], $_POST['review']);
	}else{
		echo "Could't rate";
	}

}

?>