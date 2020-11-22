<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$tutor = $_GET['tutorid'];
$availability=$_GET['availability'];
$sql = "UPDATE tutorAvailabilities SET availability = ('$availability') WHERE tutor_id = $tutor";
if (mysqli_query($con,$sql)) {
      echo "Successful";
   }
else {
  echo (mysqli_error($con));
}
?>
