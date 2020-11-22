<?php
$servername = "coms-510-04.cs.iastate.edu";
$username = "Company1";
$password = "Company@1";
$database = "Company1";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $database);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully";
?>
