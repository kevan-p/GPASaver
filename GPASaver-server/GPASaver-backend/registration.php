<?php
$response = array();
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array

	$username = $input['username'];
	$password = $input['password'];
	$email = $input['email'];

	$query = "SELECT username FROM credential WHERE username = '$username'";
	$result = mysqli_query($con,$query);
 	$row = mysqli_fetch_row($result);

if(strcmp($username, $row[0]) != 0){
	$query1 = "SELECT email FROM credential WHERE email = '$email'";
	$result1 = mysqli_query($con,$query1);
 	$row1 = mysqli_fetch_row($result1);

	if(strcmp($email, $row1[0]) != 0){
		$sql = "INSERT into credential (username, password, email) values ('$username','$password','$email')";
		if (mysqli_query($con,$sql)) {
		$response["status"] = 0;
	 	$response["message"] = "Success";
	 	}

	 }else {
		 $response["status"] = 2;
		 $response["message"] = "Email Already In Use";
	}
}else {
		 $response["status"] = 2;
		 $response["message"] = "Username Already taken";
}

 	echo json_encode($response);


?>