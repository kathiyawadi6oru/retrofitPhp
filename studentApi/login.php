<?php

require 'connection.php';

$email = $_REQUEST["email"];
$pwd = md5($_REQUEST["password"]);

$db = new DBConnect();
$con = $db->connect();

$checkuser = "SELECT * FROM `stud` WHERE `Email`='$email' AND `Password`='$pwd'";

$result = mysqli_query($con,$checkuser);

if(mysqli_num_rows($result)>0){
    while($row=$result->fetch_assoc()){
        $response['user'] = $row;
    }
    $response['error'] = 0;
    $response['message'] = "Login Successfully";
}else{
    $response['user']=(object)[];
    $response['error'] = 1;
    $response['message'] = "Login Failed";
}

echo json_encode($response);

?>