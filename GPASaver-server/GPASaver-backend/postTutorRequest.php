<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$id=$_GET['id'];
$GPA= $_GET['gpa'];
$hourly_rate= $_GET['hour'];
$course=$_GET['course'];
$sql = "INSERT into tutor values ('$id','$GPA','$hourly_rate','$course')";
if (mysqli_query($con,$sql)) {
      echo "Successful";
   }
else {
  echo (mysqli_error($con));
}
?>
