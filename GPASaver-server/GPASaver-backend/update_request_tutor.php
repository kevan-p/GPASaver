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
$tutorid = $input['tutorid'];
$courseid = $input['courseid'];
$date = $input['date'];
$approved = $input['approved'];
$sql = "UPDATE requestTutor SET approved='$approved' WHERE user_id='$userid' AND tutor_id='$tutorid' AND course_id='$courseid' AND request_date='$date' ";
if (mysqli_query($con,$sql)) {
    $response["status"] = 0;
    $response["message"] = "Request Updated";
   }
else {
    $response["status"] = 2;
    $response["message"] = mysqli_error($con);
}
echo json_encode($response);
?>
