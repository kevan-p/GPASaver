<?php
ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);

$con = mysqli_connect("coms-510-04.cs.iastate.edu", "Company1", "Company@1", "Company1");

// Check connection
if (mysqli_connect_errno()) {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$sql = "SELECT * FROM requestTutor";

if ($result = mysqli_query($con, $sql)) {
  $resultArray = array();
  $userResultArray = array();
  $courseResultArray = array();
  $tempArray = array();

  while ($row = $result->fetch_object()) {
    $tempArray = $row;
    array_push($resultArray, $tempArray);
  }

  $userSql = "SELECT * FROM users"; // TODO: Fix performance issue

  if ($userResult = mysqli_query($con, $userSql)) {
    $userTempArray = array();

    while ($userRow = $userResult->fetch_object()) {
      $userTempArray = $userRow;
      array_push($userResultArray, $userTempArray);
    }
  }

  $courseSql = "SELECT * FROM courses"; // TODO: Fix performance issue

  if ($courseResult = mysqli_query($con, $courseSql)) {
    $courseTempArray = array();

    while ($courseRow = $courseResult->fetch_object()) {
      $courseTempArray = $courseRow;
      array_push($courseResultArray, $courseTempArray);
    }
  }

  for ($i = 0; $i < count($resultArray); $i++) {
    for ($j = 0; $j < count($userResultArray); $j++) {
      if (strcmp($userResultArray[$j]->id, $resultArray[$i]->tutor_id) == 0) {
        $resultArray[$i]->tutor_full_name = $userResultArray[$j]->full_name;
      }
    }
  }

  for ($i = 0; $i < count($resultArray); $i++) {
    for ($j = 0; $j < count($userResultArray); $j++) {
      if (strcmp($userResultArray[$j]->id, $resultArray[$i]->user_id) == 0) {
        $resultArray[$i]->user_full_name = $userResultArray[$j]->full_name;
      }
    }
  }

  for ($i = 0; $i < count($resultArray); $i++) {
    for ($j = 0; $j < count($courseResultArray); $j++) {
      if (strcmp($courseResultArray[$j]->course_id, $resultArray[$i]->user_id) == 0) {
        $resultArray[$i]->course_name = $courseResultArray[$j]->course_name;
      }
    }
  }
  echo json_encode($resultArray, true);
}
mysqli_close($con);
