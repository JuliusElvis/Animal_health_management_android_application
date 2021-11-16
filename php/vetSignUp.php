<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['reg_no']) && isset($_POST['name']) && isset($_POST['address']) && isset($_POST['qualification']) && isset($_POST['phone']) && isset($_POST['locality'])) {
    if ($db->dbConnect()) {
        if ($db->docsignUp("registered_docs", $_POST['reg_no'], $_POST['name'], $_POST['address'], $_POST['qualification'], $_POST['phone'], $_POST['locality'])) {
            echo "Success";
        } else echo "Registration Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
