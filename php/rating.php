<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['username']) && isset($_POST['rating'])) {
    if ($db->dbConnect()) {
        if ($db->rateDoc("ratings", $_POST['name'], $_POST['username'], $_POST['rating'])) {
        	$db->getRating("ratings",$_POST['name']);
        }else{
        	$db->getRating("ratings",$_POST['name']);
        }
    } 
        else {
        	$db->getRating("ratings",$_POST['name']);
            
        }
} else {
    $db->getRating("ratings",$_POST['name']);
    
} 
?>
