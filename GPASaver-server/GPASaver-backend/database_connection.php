<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");
 
// Check connection
if(mysqli_connect_errno())
{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
 




function userExists($username){
	$query = "SELECT username FROM user WHERE username = ?";
	global $con;
	if($stmt = $con->prepare($query)){
		$stmt->bind_param("s",$username);
		$stmt->execute();
		$stmt->store_result();
		$stmt->fetch();
		if($stmt->num_rows == 1){
			$stmt->close();
			return true;
		}
		$stmt->close();
	}
 
	return false;

?>