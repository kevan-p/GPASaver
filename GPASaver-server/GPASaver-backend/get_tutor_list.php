<?php
$response = array();

$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$id = $_GET['id'];
//get the tutor_list of all the courses the student with id enrolled
$sql = "select tutor.course_id,tutor.tutor_id,users.full_name as 'Tutor'  from tutor,users where tutor.course_id in (select course_id from Enrollment where user_id = $id) and users.id = tutor.tutor_id";
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
