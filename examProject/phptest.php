<!DOCTYPE html>
<html lang = "en-GB">
	<head>
		<title>Homepage</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="my_style.css">	

	</head>
	
		<body>
	
	<a href="#maincontent">Skip to main content</a>
	<a href="#skiptofooter">Skip to footer</a>
					
	<a id="skiptohome"></a>
	<h1> Who's that player</h1>	




	<nav>
		<a href="index.html">Homepage</a>
		<a href ="phptest.php">List of players</a>
	</nav>
	

	
	<h2 class="Homepage">Homepage</h2>
	

	<a id="maincontent"></a>
	
	<?php
	$con = mysqli_connect("localhost","user","");
			if (!$con){
			die('Could not connect: ' . mysqli_error());}
			
			mysqli_select_db($con , "who's_that_player");
	$result = mysqli_query($con, "SELECT * FROM player_info LEFT OUTER JOIN team_info On player_info.firstName ") or die(mysqli_error($con));
	

	echo "<table>
	<tr>
	<th>Team info</th>
	<th>Team stat</th> 
	<th>First name</th>
	<th>Last name</th>
	<th>Player info</th>
	<th>Nationality</th>
	<th>Compertior number </th>
	<th>age</th>
	<th>Height</th>
	<th>Gender</th>
	<th>Apperances</th>
	<th>player Image</th>
    <br></br>
	
	</tr>";

	while($row = mysqli_fetch_array($result))
	{
		echo "<tr>";
		echo "<td>" . $row['Team_info'] . "</td>";
		echo "<td>" . $row['Statitsitcs'] . "</td>";
		echo "<td>" . $row['firstName'] . "</td>";
		echo "<td>" . $row['lastName'] . "</td>";		
		echo "<td>" . $row['Player info'] . "</td>";
		echo "<td>" . $row['Nationality'] . "</td>";
		echo "<td>" . $row['Compertior number'] . "</td>";
		echo "<td>" . $row['Age'] . "</td>";
		echo "<td>" . $row['Height'] . "</td>";
		echo "<td>" . $row['Gender'] . "</td>";
		echo "<td>" . $row['Apperances'] . "</td>";
		echo "<td><IMG SRC='".$row['Image']."'.</td>";
		echo "</tr>";
		 }
echo "</table>";
	
	?>
	
	
	
	<footer>
		<a id="skiptofooter"></a>
		<p> All copyright is held by there respective owners</p>
		<p> All sourse come directly from <a href="https://www.manutd.com/">United directly </a></p>
		<a href="#skiptohome">Skip to home</a>
		<a href="#maincontent">Skip to main content</a>
	</footer>	
	</body>

</html>
		