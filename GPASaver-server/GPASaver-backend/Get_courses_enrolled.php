<?php
$response = array();

$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$id = $_GET['id'];

$sql = "SELECT Enrollment.course_id,users.full_name,courses.course_name FROM Enrollment,users,courses where Enrollment.user_id = $id AND Enrollment.course_id = courses.course_id AND users.id = $id";
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
