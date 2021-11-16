<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name'])){
	if ($db->dbConnect()) {
		$db->getRating("ratings",$_POST['name']);
	}else{
		echo "not yet rated";
	}
}

?>
