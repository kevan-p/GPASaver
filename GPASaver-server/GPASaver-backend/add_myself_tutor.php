<?php
$response = array();
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$tutor = $_GET['tutorid'];
$course = $_GET['courseid'];
$gpa = $_GET['gpa'];
$availability = $_GET['availability'];

$sql_check = "SELECT * FROM tutor WHERE tutor_id = $tutor AND course_id = $course";
$sql_insert = "INSERT into tutor values ($tutor, $gpa, 5, $course, '$availability')"; 

if ( $result = mysqli_query($con,$sql_check) and mysqli_num_rows($result) == 0 ) 
{
	// echo 'empty';
    if( $result_insert = mysqli_query($con,$sql_insert))
    {
    	echo 'successful';
    }
    else{
    	echo (mysqli_error($con));
    }
}
else{
	echo 'exists';
}

mysqli_close($con);


?>
