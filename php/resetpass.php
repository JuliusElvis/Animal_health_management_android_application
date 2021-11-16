<?php
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require 'C:/Users/JULIUSELVIS/vendor/autoload.php';

require "conn.php";
require "PHPMailer.php";
require "SMTP.php";
require "Exception.php";

function random_str($length, $keyspace = '0123456789'){
  $pieces = [];
  $max = mb_strlen($keyspace, '8bit') - 1;
  for($i=0;$i<$length;++$i){
    $pieces [] = $keyspace[random_int(0, $max)];
  }
  return implode('', $pieces);
}

if (isset($_POST['email'])){
$email =  $_POST['email'];}
$b = random_str(6);
$hashed_b = hash('sha256', $b);
$forgotpassword_message = "Your account verification code is: $b";

$selectquery = "SELECT email FROM users WHERE email = '$email'";
$resultquery = mysqli_query($conn, $selectquery);
$varresult = mysqli_num_rows($resultquery);

if ($varresult == 1){
	$updatepassword = "UPDATE users SET verif_code = '$hashed_b' WHERE email = '$email'";
    //$updatepassword = "UPDATE users SET verif_code = '$b' WHERE email = '$email'";
	if (mysqli_query($conn, $updatepassword)){

		$mail = new PHPMailer(true);
		try {
        //Server settings
        $mail->SMTPDebug = 0;                                 // Enable verbose debug output
        $mail->isSMTP();                                      // Set mailer to use SMTP
        $mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
        $mail->SMTPAuth = true;                               // Enable SMTP authentication
        $mail->Username = 'juliuselvis56@gmail.com';                 // SMTP username. Change this to your email
        $mail->Password = 'juliuselvis99';                           // SMTP password. Change this to your password
        $mail->SMTPSecure = 'tls';
        //$mail->SMTPSecure = 'ssl';                            // Enable TLS encryption, `ssl` also accepted
        $mail->Port = 587;
        //$mail->Port = 465;

        //Recipients
        $mail->setFrom($email, 'Livestock Manager'); // Add a recipient               // Name is optional
        //$mail->addAddress('juliuselvis56@gmail.com');
        $mail->addAddress($email);

        //Content
        $mail->isHTML(true);                                  // Set email format to HTML
        $mail->Subject = 'Account verification code';
        $mail->Body    = $forgotpassword_message;
        $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

        $mail->send();
        echo 'Message has been sent';

	}catch (Exception $e) {
        echo 'Message could not be sent. Mailer Error: ', $mail->ErrorInfo;
        }

}else{
	echo "failed";
}
}else{ echo "no account";}

?>
