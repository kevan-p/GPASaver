<?php
$con=mysqli_connect("coms-510-04.cs.iastate.edu","Company1","Company@1","Company1");

// Check connection
if (mysqli_connect_errno())
{
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$sql = "select users.id, users.full_name, users.phone, users.image_url, courses.course_name from users left join tutor on users.id = tutor.tutor_id left join courses on tutor.course_id = courses.course_id where users.Tutor = true";
$result = mysqli_query($con, $sql);
$rows = array();
if (mysqli_num_rows($result) > 0) {
  while($row = mysqli_fetch_assoc($result)) {
    $rows[] = $row;
  }
  echo json_encode($rows);
}
else {
  echo (mysqli_error($con));
}
?>
