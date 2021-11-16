<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['reg_no'])){
	if ($db->dbConnect()) {
		$db->getVetName("registered_docs",$_POST['reg_no']);
	}else{
		echo "No name";
	}
}

?>
