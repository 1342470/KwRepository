	<?php
	$con = mysqli_connect("localhost","user","");
			if (!$con){
			die('Could not connect: ' . mysqli_error());}
			
			mysqli_select_db($con , "who's_that_player");
	$result = mysqli_query($con, "SELECT * FROM player_info") or die(mysqli_error($con));
	echo "<table>
	<tr>
	<th>Name</th>
	<th>Player info</th>
	<th>Nationality</th>
	<th>Compertior number </th>
	<th>age</th>
	<th>Height</th>
	<th>Gender</th>
	<th onClick >player Image</th>
	</tr>";

	while($row = mysqli_fetch_array($result))
	{
      echo "<tr>";
      echo "<td>" . $row['Name'] . "</td>";
      echo "<td>" . $row['Player info'] . "</td>";
      echo "<td>" . $row['Nationality'] . "</td>";
	  echo "<td>" . $row['Compertior number'] . "</td>";
	  echo "<td>" . $row['Age'] . "</td>";
	  echo "<td>" . $row['Height'] . "</td>";
	  echo "<td>" . $row['Gender'] . "</td>";
      echo "<TD><IMG SRC='".$row['Image']."'. " test test"</TD>";
      echo "</tr>";
		}
echo "</table>";
	
	?>
	