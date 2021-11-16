<?php 
require_once 'conn.php';

if (isset($_GET['key'])) {

	$key = $_GET["key"];
    
        
        $query = "SELECT * FROM registered_docs WHERE name LIKE '%$key%' OR address LIKE '%$key%'";
        $result = mysqli_query($conn, $query);
        $response = array();
        while( $row = mysqli_fetch_assoc($result) ){
            array_push($response, 
            	array( 
                'name'=>$row['name'], 
                'address'=>$row['address'],
            	'qualification'=>$row['qualification'])
                //'phone'=>$row['phone']
                //'locality'=>$row['locality']) 
            );
        }
        echo json_encode($response);
    }

else{
        
        
        $query = "SELECT * FROM registered_docs";
        $result = mysqli_query($conn, $query);
        $response = array();
        while( $row = mysqli_fetch_assoc($result) ){
            array_push($response, 
            array( 
                'name'=>$row['name'],
                'address'=>$row['address'],
            	'qualification'=>$row['qualification'])
                //'phone'=>$row['phone']
                //'locality'=>$row['locality']) 
            );
        }
        echo json_encode($response);   
    }

mysqli_close($conn);

 ?>