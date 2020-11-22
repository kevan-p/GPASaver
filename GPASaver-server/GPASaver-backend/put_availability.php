<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$tutor = $_GET['tutorid'];
$course = $_GET['courseid'];
$availability=$_GET['availability'];
$sql = "INSERT into requestTutor values ('$availability') WHERE tutor_id = $tutor AND course_id = $course";
if (mysqli_query($con,$sql)) {
      echo "Successful";
   }
else {
  echo (mysqli_error($con));
}
?>
