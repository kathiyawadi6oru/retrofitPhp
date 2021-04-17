<?php
    require "connection.php";

    $Name = $_REQUEST["name"];
    $useremail = $_REQUEST["email"];
    $userpassword = $_REQUEST["password"];


    $db = new DBConnect();
    $con = $db->connect();

    $insertQuery = "INSERT INTO `stud`(`Stud_name`, `email`, `password`) 
                    VALUES ('$Name','$useremail','$userpassword')";

    $result = mysqli_query($con,$insertQuery);

    if ($result) {
        # code...
        echo "Data Added Successfully";
    }else {
        echo " Insert Faild";
    }
    
?>