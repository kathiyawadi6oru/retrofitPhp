<?php

   require "connection.php";


   $db = new DBConnect();
   $con  = $db->connect();

   $checkExstingUser = "SELECT `Stud_id`,`Stud_name`,`Email` FROM `stud`";

   $checkResult = mysqli_query($con,$checkExstingUser);

   if(mysqli_num_rows($checkResult) > 0){
            while($row=$checkResult->fetch_assoc()){
                $response['user'][] = $row;
                $response["error"] = 0;
           }
        }else{
        $response["user"] = (object)[];
        $response["error"] = 1;
       }

    echo json_encode($response);

?>