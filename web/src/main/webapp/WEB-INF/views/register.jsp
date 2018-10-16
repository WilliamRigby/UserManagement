<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-xs-6 col-xs-offset-1">
        <p>Register New User</p>
        <c:if test="${not empty invalidUsername}"><div class="backend-error-msg">${invalidUsername}</div></c:if>
        <c:if test="${not empty invalidPassword}"><div class="backend-error-msg">${invalidPassword}</div></c:if>
        <c:if test="${not empty duplicateUsername}"><div class="backend-error-msg">${duplicateUsername}</div></c:if>
        <c:if test="${not empty errorMessage}"><div class="backend-error-msg">${errorMessage}</div></c:if>
        <ul>
            <li class="requirement-item">Username must contain a minimum of three characters, and can only contain letters and numbers</li>
            <li class="requirement-item">Password has a minimum of eight characters, at least one uppercase letter, one lowercase letter, one number and one special character</li>
        </ul>
        <form id="registerForm" action="/adduser" method="POST">
            <div class="form-group">
                <label for="username">Username:</label>
                <div class="flex-box">
                    <input id="usernameInput" type="text" class="form-control" name="username">
                    <div id="usernameWarning" class="red-asterisk">* Please check the username requirements</div>
                </div>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <div class="flex-box">
                    <input id="passInput" type="password" class="form-control" name="password">
                    <div id="passwordWarning" class="red-asterisk">* Please check the password requirements</div>
                </div>
            </div>

            <input type="submit" class="btn btn-default" value="submit">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
</div>

<script type="text/javascript" src="<c:url value='/resources/js/register.js'/>"></script>