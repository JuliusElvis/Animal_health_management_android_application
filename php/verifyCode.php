<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['email']) && isset($_POST['verif_code'])) {
    if ($db->dbConnect()) {
        if ($db->verCode("users", $_POST['email'], $_POST['verif_code'])) {
            echo "Success";
        } else echo "Something went wrong";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
