<?php
$response = array();

$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

 
//Get the input request parameters
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array
 
//Check for Mandatory parameters
	$id = $input['id'];
	$balance = $input['balance'];
	$sql = "UPDATE users SET balance='$balance' WHERE id='$id' ";
	$result = mysqli_query($con,$sql);
				$response["status"] = 0;
				$response["message"] = "Uppdate successful";
	echo json_encode($response);

?>