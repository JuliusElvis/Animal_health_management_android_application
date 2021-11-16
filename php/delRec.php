<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['reg_no']) && isset($_POST['name'])) {
    if ($db->dbConnect()) {
        if ($db->deleteRec("registered_docs", $_POST['reg_no'], $_POST['name'])) {
            echo "Delete Success";
        } else echo "Record Delete Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>