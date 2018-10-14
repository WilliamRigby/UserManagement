<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<head>
    <link rel="stylesheet" href="<c:url value='/resources/style.css'/>">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Rigby4j</a>
    </div>
    <ul class="nav navbar-nav">
        <li class="active"><a href="/">Home</a></li>
        <security:authorize access="isAuthenticated()">
            <li><a href="/restricted/">User Restricted</a></li>
        </security:authorize>
        <li class="active"><a href="/usermanagement/list/">User Management</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <security:authorize access="isAuthenticated()">
            <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='/logout'">Logout</button>
        </security:authorize>
        <security:authorize access="!isAuthenticated()">
            <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='/login'">Login</button>
            <button type="button" class="btn btn-default btn-lg" onclick="window.location.href='/register'">Register</button>
        </security:authorize>
    </ul>
  </div>
</nav>

<body>
    <jsp:include page="/WEB-INF/views/${view}.jsp"></jsp:include>
</body>