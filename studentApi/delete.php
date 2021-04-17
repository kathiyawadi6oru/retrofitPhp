<?php

   require "connection.php";

   $id = $_REQUEST["id"];

   $db = new DBConnect();
   $con  = $db->connect();

   $deleteUser = "DELETE FROM `stud` WHERE `Stud_id`='$id'";

   $result = mysqli_query($con,$deleteUser);

    if($result){
        $response['error'] = 0;
        $response['message'] = "Delete Successfully";    
    }else{
        $response['user']=(object)[];
        $response['error'] = 1;
        $response['message'] = "Delete Failed";
    }

echo json_encode($response);

?>