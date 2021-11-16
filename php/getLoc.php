<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name'])){
	if ($db->dbConnect()) {
		$db->getloc("registered_docs",$_POST['name']);
	}else{
		echo "failed";
	}
}

?>
