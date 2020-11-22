<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
    $response["status"] = 1;
    $response["message"] = "Failed to connect to MySQL: " . mysqli_connect_error();
    echo response;
}
//Get the input request parameters
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array

$userid = $input['userid'];
$fname = $input['fname'];
$lname = $input['lname'];
$tutorid = $input['tutorid'];
$courseid = $input['courseid'];
$date = $input['date'];
$ftime = $input['ftime'];
$etime = $input['etime'];
$cost = $input['cost'];
$sql = "INSERT into requestTutor values ('$userid','$fname','$lname','$tutorid', '$courseid', '$date','$ftime','$etime','$cost','0')";
if (mysqli_query($con,$sql)) {
    $response["status"] = 0;
    $response["message"] = "Request Sent";
   }
else {
    $response["status"] = 2;
    $response["message"] = mysqli_error($con);
}
echo json_encode($response);
?>
