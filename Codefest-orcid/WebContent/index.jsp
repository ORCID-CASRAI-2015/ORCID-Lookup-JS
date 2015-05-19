<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Codefest</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" 
  href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script>
  $(document).ready(function() {
      $(function() {
         $("#search").autocomplete({     
	         source : function(request, response) {
	         $.ajax({
                 url : "Controller",
                 type : "GET",
                 data : {
                         term : request.term
                 },
                 dataType : "json",
                 success : function(data) {
                         response(data);
                 }
         });},
         autoFocus: true
		});
        $( "#search" ).on( "autocompleteselect", function( event, ui ) {
        	$("#bio").val(ui.item.value.orcidBio.biography.content)
      	    $("#search").val((ui.item.label.split(" # "))[0]);
        	$("#activity").val(ui.item.works);
      	    $("#profile").val(ui.item.xml);
	      	$('#gName').val(ui.item.value.orcidBio.personalDetails.givenNames.content);
	    	$('#fName').val(ui.item.value.orcidBio.personalDetails.familyName.content);
	    	$('#cName').val(ui.item.value.orcidBio.personalDetails.creditName.content);
	    	$('#oName').val(ui.item.otherNames);
	    	$('#url').val(ui.item.urls);
	    	$('#tags').val(ui.item.keywords);
	    	$('#addr').val(ui.item.value.orcidBio.contactDetails.address.country.value);
      	    event.preventDefault();
      	    } );
      
      });
	});
  function clearForm() {
	  $('#bio').val("");
	  $('#profile').val("");
	  $('#search').val("");
	  $("#activity").val("");
	  $('#gName').val("");
	  $('#fName').val("");
	  $('#cName').val("");
	  $('#oName').val("");
	  $('#url').val("");
	  $('#tags').val("");
	  $('#addr').val("");
  }
  </script>
</head>
<body>
<%@include file="logo.jsp" %>
<center>
<font color="red">Search here by Name, Orcid or by Country</font>
  <div class="ui-widget"><br>
 ORCID : <input type="text" id="search" name="search" class="search" />
 &nbsp;&nbsp;<input type="button" name="Reset" value="Reset" onclick="clearForm()"/><br><br>
 
<hr/>
	<div style="background-color: #EEE;">
	<br>
	<table border="1">
		<tr>
			<td>
				<table>
					<tr>
						<th>Profile Data</th>
					</tr>
				</table>
			</td>
		</tr>
		<tr style="vertical-align: top;">
			<td>
				<table>
					<tr>
						<td>Biography :</td>
						<td><textarea rows = "3" cols = "30" name="bio" id="bio" disabled="disabled"></textarea></td>
					</tr>
					<tr>
						<td></td>
						<td><br></td>
						
					</tr>
					<tr>
						<td>Given Name :</td>
						<td><input type ="text" disabled="disabled" name="gName" id="gName" style="width: 320px;"/></td>
					</tr>
					<tr>
						<td>Family Name :</td>
						<td><input type ="text" disabled="disabled" name="fName" id="fName" style="width: 320px;"/></td>
					</tr>
					<tr>
						<td>Credit Name :</td>
						<td><input type ="text" disabled="disabled" name="cName" id="cName" style="width: 320px;"/></td>
					</tr>
					<tr>
						<td>Other Names :</td>
						<td><input type ="text" disabled="disabled" name="oName" id="oName" style="width: 320px;"/></td>
					</tr>
					<tr>
						<td>URL :</td>
						<td><input type ="text" disabled="disabled" name="url" id="url" style="width: 320px;"/></td>
					</tr>
					<tr>
						<td>Tags :</td>
						<td><input type ="text" disabled="disabled" name="tags" id="tags" style="width: 320px;"/></td>
					</tr>
					<tr>
						<td>Address :</td>
						<td><input type ="text" disabled="disabled" name="addr" id="addr" style="width: 320px;"/></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr style="vertical-align: top;">
						<td>Profile XML :</td>
						<td><textarea rows = "20" cols = "50" name="profile" id="profile" disabled="disabled"></textarea></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr style="vertical-align: top;">
						<td>Profile Works :</td>
						<td><textarea rows = "20" cols = "50" name="activity" id="activity" disabled="disabled"></textarea></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
 	</div>
</div>
<div style="background-color: #EEE;">
<br><br><br><br>
	<a href="http://codefest.orcid-casrai-2015.org/" target=_blank>Visit http://codefest.orcid-casrai-2015.org/</a>
</div>
</center>
</body>
</html>