 <%--
 * ===================================================
 * Copyright 2012 AEGIS.net, Inc.
 *
 * Description:  
 * The default jsp file as configured in web.xml
 * of this web application.
 * ===================================================
 --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="css/calendarview.css" />
    <style> 
          body {
            font-family: Trebuchet MS;
          }
      </style> 
      <title>Potbelly Sandwich Shops - Washington DC</title>
    </head>
    <body onload="initialize();" onunload="google.maps.Unload();">
      <table>
        <tr>
          <td><a href="http://www.aegis.net">
              <img alt="Potbelly.com logo" src="Potbelly_logo.png" 
                align="middle" width="200px"/></a></td>
            <td style="padding-left: 10px;">
              <h1>Potbelly Sandwich Shops - Washington DC</h1>
            </td>
        </tr>
      </table>
      <hr style="color: red;" />
      <p>Welcome to the Potbelly Shops - Washington DC community.</p>
      <p>All our customers love Potbelly and that is why we are the best place for Lunch and 
         your office catering. Please give us a call... we will take good care of you!!!</p>
      <p>Breakfast served 7:30am to 11am.</p>
        <div id="map" style="width: 750px; height: 350px;"></div>
        <table style="width: 900px;">
          <tr>
            <td style="width: 100%;">
              <div id="output">Filling in the potbelly shops data from Washington, DC...</div>
              <div id="resultDiv"></div>
            </td>
          </tr>
        </table>
        <script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAAF0RqRyWNd_7X3e0RobCNCBTwM0brOpm-All5BF6PoaKBxRWWERRUp8BqaOUOl7m4T_bw9zBspjgKPw"></script>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
        <script type="text/javascript">
            google.load("prototype", "1.6.0.3");
        </script>
        <script type="text/javascript" src="js/calendarview.js"></script>
        <script type="text/javascript">
            var map;
            var markersArray = [];
            var results = "";
 
            // Call this function when the page has been loaded
            function initialize() {
              var myOptions = {
                zoom: 13,
                center: new google.maps.LatLng(38.8900, -77.0095),
                zoomControl: true,
                scaleControl: true,
                mapTypeId: google.maps.MapTypeId.ROADMAP
              };

              map = new google.maps.Map($("map"), myOptions);
              plotResults();
            }

            function plotResults() {
                results = "";

                new Ajax.Request('PotBellyService.groovy',{
                    method: 'get',
                    onSuccess: function(xhr) {
                        var output = $('output');
                        var resp = xhr.responseXML.getElementsByTagName('potbellyshop');
                        clearMarkers();
                        markersArray = new Array();
                        for (var i = 0; i < resp.length; i++) {
                            var potbellyshop = resp[i];
                            addMarker(potbellyshop);
                        }
                        output.update("<h2>Number of visitors to the Potbelly shop.</h2>");
                        $('resultDiv').innerHTML = "<table border='1' width='100%'><tr><th>Address</th><th>Number of visitors</th></tr>" + results + "</table>";
                    }
                });

                function addMarker(potbellyshop) {
                    var outcome = potbellyshop.attributes.getNamedItem('outcome').value;
                    var visitors = potbellyshop.attributes.getNamedItem('visitors').value;
                    var lat = potbellyshop.attributes.getNamedItem('lat').value;
                    var lng = potbellyshop.attributes.getNamedItem('lng').value;
                    var point = new google.maps.LatLng(lat,lng);
                    var marker = new google.maps.Marker({
                      position: point,
                      map: map,
                      title: outcome + ' Visitors today:' + visitors
                    });
                    markersArray.push(marker);
                    results += "<tr>";
                    results += "<td>" + outcome + "</td>";
                    results += "<td>" + visitors + "</td>";
                    results += "</tr>";
                }
                
                function clearMarkers() {
                  if (markersArray.length > 0) {
                    for (i in markersArray) {
                      marker = markersArray.shift();
                      marker.setMap(null);
                      marker = null;
                    }
                  }
                }
            }
        </script>
    </body>
</html>
