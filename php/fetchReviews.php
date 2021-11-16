<?php 
require_once 'conn.php';

$query = "SELECT username,review FROM app_rating";
$result = mysqli_query($conn, $query);
$response = array();
while ($row = mysqli_fetch_assoc($result)) {
	array_push($response,
		array(
			'username'=>$row['username'],
			'review'=>$row['review']));
}
echo json_encode($response);
?>