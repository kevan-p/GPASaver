<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if ($con->connect_errno)
{
  echo "Failed to connect to MySQL:";
  exit();
}

$data = array();

$sql = "SELECT * FROM user_request";
$result = $con->query($sql);

while ($row = $result->fetch_assoc()){
	$data[] = $row;
}

echo json_encode($data);

?>
