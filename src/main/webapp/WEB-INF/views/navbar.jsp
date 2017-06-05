<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rigatron.rigs4j.models.User"%>

<head>
	<link rel='stylesheet' href="<c:url value='/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
    <script src="<c:url value='/resources/jquery-3.2.1.min.js'/>"></script>
    <script src="<c:url value='/resources/bootstrap-3.3.7-dist/js/bootstrap.js'/>"></script>
</head>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Rigby4j</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/">Home</a></li>
      <li><a href="/aboutme/">About Me</a></li>
      <li><a href="/interests/">Interests</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <!-- <li><a href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
      <!-- <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li> -->
      <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalLogin">Login</button>
      <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#modalRegister">Register</button>
    </ul>
  </div>
</nav>

<div id="modalLogin" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <p>Some text in the modal.</p>
        <div class="form-group">
          <label for="login_usr">Name:</label>
          <input type="text" class="form-control" id="login_usr">
        </div>
        <div class="form-group">
          <label for="login_pwd">Password:</label>
          <input type="password" class="form-control" id="login_pwd">
        </div>
      </div>
      <div class="modal-footer">
        <button id='login_btnSubmit' type="button" class="btn btn-default" data-dismiss="modal">Submit</button>
      </div>
    </div>
  </div>
</div>

<div id="modalRegister" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <p>Some text in the modal.</p>
        <div class="form-group">
          <label for="register_usr">Name:</label>
          <input type="text" class="form-control" id="register_usr">
        </div>
        <div class="form-group">
          <label for="register_pwd">Password:</label>
          <input type="password" class="form-control" id="register_pwd">
        </div>
      </div>
      <div class="modal-footer">
        <button id='register_btnSubmit' type="button" class="btn btn-default" data-dismiss="modal">Submit</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">


    $(document).ready(function() {
        $("#register_btnSubmit").click(function(){

            var data = {}
            var username = $("#register_usr").val();
            var password = $("#register_pwd").val();
            data["username"] = username;
            data["password"] = password;

            var json = JSON.stringify(data)

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "adduser/",
                data: json,
                dataType: 'json',
                timeout: 600000,
                success: function (data) {
                    alert("ajax success")
                },
                error: function (e) {
                    alert("ajax error")
                }
            });
        });
    });

    $(document).ready(function() {
        $("#login_btnSubmit").click(function(){

            var data = {}
            var username = $("#login_usr").val();
            var password = $("#login_pwd").val();
            data["username"] = username;
            data["password"] = password;

            var json = JSON.stringify(data)

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "login/",
                data: json,
                dataType: 'json',
                timeout: 600000,
                success: function (data) {

                    var response = data[0]["response"];

                    if(response.toString() == "match") {


                        alert("You are now 'logged in'")
                    } else {
                        alert("incorrect password or something")
                    }

                },
                error: function (e) {
                    alert("There was an error")
                }
            });
        });
    });

</script>