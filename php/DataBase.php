<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            //if ($dbusername == $username && password_verify($password, $dbpassword)) {
                if ($dbusername == $username && $password == $dbpassword) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $email, $fullname, $username, $password)
    {
        $email = $this->prepareData($email);
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        //$password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (email, fullname, username, password ) VALUES ('" . $email . "','" . $fullname . "','" . $username . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function vetSignUp($table,$phone,$locality,$reg_no)
    {
        $phone = $this->prepareData($phone);
        $locality = $this->prepareData($locality);
        $reg_no = $this->prepareData($reg_no);

        $this->sql =
            "UPDATE " . $table . " SET phone = '" . $phone . "', locality= '" . $locality . "' WHERE reg_no = '" . $reg_no . "'";
            
            if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function verifyReg($table,$reg_no)
    {
        $reg_no = $this->prepareData($reg_no);
        $this->sql = "select * from " . $table . " where reg_no = '" . $reg_no . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
                $verifyReg = true;
        } else $verifyReg = false;

        return $verifyReg;
    }
    function rateDoc($table,$name,$username,$rating)
    {
      $name = $this->prepareData($name);
      $username = $this->prepareData($username);
      $rating = $this->prepareData($rating);
      $this->sql = "select * from " . $table . " where name = '" . $name . "' and username = '" . $username . "'";
      $result = mysqli_query($this->connect, $this->sql);
      $row = mysqli_fetch_assoc($result);
      if (mysqli_num_rows($result) == 0) {
        $this->sql =
        "INSERT INTO " . $table . " (name, username, rating) VALUES ('" . $name . "','" . $username . "','" . $rating . "')";
        if(mysqli_query($this->connect, $this->sql)){
            return true;
        } else return false;
      }else return false;
        
    }

    function getRating($table,$name){
        $name = $this->prepareData($name);
        $this->sql = "select avg(rating) from " . $table . " where name = '" . $name . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if (mysqli_num_rows($result) != 0) {
            while($row = mysqli_fetch_assoc($result)){
            echo $row['avg(rating)'];
        }   
        }else{
            echo "not yet rated";      
    }
        }

    function appRating($table,$username,$rating,$review){
        $username = $this->prepareData($username);
        $rating = $this->prepareData($rating);
        $review = $this->prepareData($review);
        $this->sql = "select * from " . $table . " where username = '" . $username . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) == 0) {
        $this->sql =
            "INSERT INTO " . $table . " (username, rating, review ) VALUES ('" . $username . "','" . $rating . "','" . $review . "')";
            mysqli_query($this->connect, $this->sql);
        }
        else{
           $this->sql =
            "UPDATE " . $table . " SET rating = '" . $rating . "', review = '" . $review . "' WHERE username = '" . $username . "'"; 
            mysqli_query($this->connect, $this->sql);
        }
    }

    function getAppRating($table,$username){
        $username = $this->prepareData($username);
        $this->sql = "select avg(rating) from " . $table . "";
        $result = mysqli_query($this->connect, $this->sql);
        if (mysqli_num_rows($result) != 0) {
            while($row = mysqli_fetch_assoc($result)){
            echo $row['avg(rating)'];
        }   
        }else{
            echo "not yet rated";      
    }
        }

    function getVetName($table,$reg_no){
        $reg_no = $this->prepareData($reg_no);
        $this->sql = "select name from " . $table . " where reg_no = '" . $reg_no . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if (mysqli_num_rows($result) != 0) {
            while($row = mysqli_fetch_assoc($result)){
            echo $row['name'];
        }   
        }
    } 

     function deleteRec($table,$reg_no,$name){
        $reg_no = $this->prepareData($reg_no);
        $name = $this->prepareData($name);
        $this->sql = "DELETE FROM " . $table . " WHERE reg_no = '" . $reg_no . "' AND name = '" . $name . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if ($result) {
            return true;
        }else return false;
     } 


     function docsignUp($table, $reg_no, $name, $address, $qualification, $phone, $locality)
    {
        $reg_no = $this->prepareData($reg_no);
        $name = $this->prepareData($name);
        $address = $this->prepareData($address);
        $qualification = $this->prepareData($qualification);
        $phone = $this->prepareData($phone);
        $locality = $this->prepareData($locality);
        $this->sql =
            "INSERT INTO " . $table . " (reg_no, name, address, qualification, phone, locality) VALUES ('" . $reg_no . "','" . $name . "','" . $address . "','" . $qualification . "','" . $phone . "','" . $locality . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    

    function getphone($table,$name){
        $name = $this->prepareData($name);
        $this->sql = "select phone from " . $table . " where name = '" . $name . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if (mysqli_num_rows($result) != 0) {
            while($row = mysqli_fetch_assoc($result)){
            echo $row['phone'];
        }   
        }

        }

    function getloc($table,$name){
        $name = $this->prepareData($name);
        $this->sql = "select locality from " . $table . " where name = '" . $name . "'";
        $result = mysqli_query($this->connect, $this->sql);
        if (mysqli_num_rows($result) != 0) {
            while($row = mysqli_fetch_assoc($result)){
            echo $row['locality'];
        }   
        }
        }

    function changepass($table,$email,$password)
    {
        $email = $this->prepareData($email);
        $password = $this->prepareData($password);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "UPDATE " . $table . " SET password = '" . $password . "' WHERE email = '" . $email . "'";  
            if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    /*function resetpass($table,$email){
        $email = $this->prepareData($email);

    }*/
    function verCode($table, $email, $verif_code)
    {
        $email = $this->prepareData($email);
        $verif_code = $this->prepareData($verif_code);
        $verif_code = hash('sha256', $verif_code);
        $this->sql = "select * from " . $table . " where email = '" . $email . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbemail = $row['email'];
            $dbverif_code = $row['verif_code'];
            if ($dbemail == $email && $dbverif_code == $verif_code){
                $verCode = true;
            } else $verCode = false;
        } else $verCode = false;

        return $verCode;
    }

    function openAdm($table, $username, $password){
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $password = hash('sha256', $password);
        $this->sql = "select * from " . $table . " where password = '" . $password . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            if ($dbusername == $username && $password == $dbpassword) {
                $openAdm = true;
            } else $openAdm = false;
        } else $openAdm = false;

        return $openAdm;
     } 

    function addAdm($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        //$password = password_hash($password, PASSWORD_DEFAULT);
        $password = hash('sha256', $password);
        $this->sql =
            "INSERT INTO " . $table . " (username, password ) VALUES ('" . $username . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }



}

?>
