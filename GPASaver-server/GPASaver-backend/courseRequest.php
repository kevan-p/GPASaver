<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$sql = "SELECT * FROM courses";
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
