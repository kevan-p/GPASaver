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
if(isset($input['username']) && isset($input['password'])){
	$username = $input['username'];
	$password = $input['password'];
	$query    = "SELECT password,user_id,username,email FROM credential WHERE username = '$username'";

 	$result = mysqli_query($con,$query);
 	$row = mysqli_fetch_row($result);

	if(strcmp($password, $row[0]) == 0){

		$query  = "SELECT * FROM users WHERE id = '$row[1]'";
		$result = mysqli_query($con,$query);
 		$row1   = mysqli_fetch_row($result);

		$response["status"] = 0;
		$response["message"] = "Login successful";
		$response["password"] = $row[0];
		$response["user_id"] = $row[1];
		$response["username"] = $row[2];
		$response["email"] = $row[3];
		$response["full_name"] = $row1[1];
		$response["phone"] = $row1[2];
		$response["balance"] = $row1[9];
		$response["image"] = $row1[6];
		$response["is_tutor"] = $row1[8];

	}else{
		$response["status"] = 2;
		$response["message"] = "Invalid Username/Password";
	}



 }
else{
	$response["status"] = 2;
	$response["message"] = "Missing mandatory parameters";
}
//Display the JSON response
echo json_encode($response);
?>