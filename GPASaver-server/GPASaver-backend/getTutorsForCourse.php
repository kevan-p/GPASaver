<?php
$response = array();

$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

// $tutor = $_GET['tutorid'];
$course = $_GET['courseid'];

// $sql = "SELECT full_name, tutor_id
// 		FROM Company1.tutor 
// 		INNER JOIN Company1.users 
// 		ON tutor.tutor_id=users.id
// 		WHERE course_id = $course";

$sql = "SELECT tutor_id, full_name, phone, email
		FROM Company1.tutor 
		INNER JOIN Company1.users 
		ON tutor.tutor_id=users.id
		INNER JOIN Company1.credential
		ON tutor.tutor_id=user_id
		WHERE course_id = $course";

if ($result = mysqli_query($con, $sql))
{
 $resultArray = array();
 $tempArray = array();

 while($row = $result->fetch_object())
 {
 $tempArray = $row;
     array_push($resultArray, $tempArray);
 }
 echo json_encode($resultArray);
}
mysqli_close($con);
?>