<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$tutor = $_GET['tutorid'];
$rating = $_GET['rating'];
$feedback= $_GET['feedback'];
$sql = "INSERT into reviewTutor values ('$tutor','$rating', '$feedback')";
if (mysqli_query($con,$sql)) {
      echo "Successful";
   }
else {
  echo (mysqli_error($con));
}
?>
