<?php

   require "connection.php";

   $userName = $_REQUEST["username"];
   $userEmail = $_REQUEST["email"];
   $userPassword = md5($_REQUEST["password"]);

   $db = new DBConnect();
   $con  = $db->connect();

   $checkExstingUser = "SELECT * FROM `stud` WHERE `Email`='$userEmail'";

   $checkResult = mysqli_query($con,$checkExstingUser);

   if(mysqli_num_rows($checkResult) > 0){
    $response["error"] = 1;
    $response["message"] = "You already have account.Try using diffrent Email ID.";
   }else{

    $insertQuery = "INSERT INTO `stud`(`Stud_name`, `Email`, `Password`) VALUES ('$userName',
                    '$userEmail','$userPassword')";
                    

        $result = mysqli_query($con,$insertQuery);

        if($result){
            $response["error"] = 0;
            $response["message"] = "Register Successfully";
        }else{  
            $response["error"] = 1;
            $response["message"] = "Registration Failed";
        }

    }

    echo json_encode($response);

?>