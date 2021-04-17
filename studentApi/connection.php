<?php

class DBConnect{

    private $con;

    function _constructor(){

    }

    function connect(){

        $hostname="localhost";
        $username="root";
        $userpass="";
        $dbname="student";

        $this->con = new mysqli($hostname,$username,$userpass,$dbname);

        if(mysqli_connect_errno()){
            echo "Failed to connect to MySQL".mysqli_connect_error();
            return null;
        }

        return $this->con;
    }
}



?>