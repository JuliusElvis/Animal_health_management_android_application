<?php
require "Database.php";
$db = new DataBase();

if (isset($_POST['phone']) && isset($_POST['locality']) && isset($_POST['reg_no'])) {
    if ($db->dbConnect()) {
        if ($db->vetSignUp("registered_docs", $_POST['phone'], $_POST['locality'], $_POST['reg_no'])) {
            echo "Registration Success";
        } else echo "Registration Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";

?>
