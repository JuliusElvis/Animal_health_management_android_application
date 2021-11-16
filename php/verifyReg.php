<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['reg_no'])) {
    if ($db->dbConnect()) {
        if ($db->verifyReg("registered_docs", $_POST['reg_no'])) {
            echo "Verification Success";
        } else echo "Registration Number not found";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
