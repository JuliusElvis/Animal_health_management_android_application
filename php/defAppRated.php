<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username'])){
	if ($db->dbConnect()) {
		$db->getAppRating("app_rating",$_POST['username']);
	}else{
		echo "not yet rated";
	}
}
?>