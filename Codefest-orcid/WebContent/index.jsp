<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Autocomplete - Default functionality</title>
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
            	    //alert(ui.item.value);
            	    search = (ui.item.label.split(" # "))[0];
            	    profile = ui.item.value;
            	    $("#search").val(search);
            	    $("#profile").val(profile);
            	    event.preventDefault();
            	    } );
      
      });
});
  </script>
</head>
<body>
  <div class="ui-widget">
 ORCID : <input type="text" id="search" name="search" class="search" /><br><br>
 
 --------------------------------------------------------PROFILE DATA----------------------------------------------------<br><br>
 Profile :<br> <textarea rows = "30" cols = "50" name="profile" id="profile"></textarea><br>
  </div>
</body>
</html>