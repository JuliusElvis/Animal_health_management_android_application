<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->changepass("users", $_POST['email'], $_POST['password'])) {
            echo "Update Success";
        } else echo "Update Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>