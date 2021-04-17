<?php

   require "connection.php";

   $id = $_REQUEST["id"];
   $userName = $_REQUEST["username"];
   $userEmail = $_REQUEST["email"];
   

   $db = new DBConnect();
   $con  = $db->connect();

   $updateUser = "UPDATE `stud` SET `Stud_name`='$userName', `Email`='$userEmail' where `Stud_id`='$id'";

   $updateresult = mysqli_query($con,$updateUser);
    if($updateresult>0){

       $checkuser = "SELECT * FROM `stud` WHERE `Email`='$userEmail'";
       $result = mysqli_query($con,$checkuser);

       if(mysqli_num_rows($result)>0){
           while($row=$result->fetch_assoc()){
               $response['user'] = $row;
           }
           $response['error'] = 0;
           $response['message'] = "Update Successfully";
        }
    }else{
           $response['user']=(object)[];
           $response['error'] = 1;
           $response['message'] = "Update Failed";
        }
       
        echo json_encode($response);
?>