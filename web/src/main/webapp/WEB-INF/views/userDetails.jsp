<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rigatron.rigs4j.web.models.User, com.rigatron.rigs4j.web.models.UserRole" %>

<div class="row">
    <div class="col-xs-4 col-xs-offset-2">
        <form>
            <p>Update User</p>
            <div style="display: flex; flex-direction: row;">
                <div>Username:</div>
                <div>${user.username}</div>
            </div>
            <input type="text" value="${user.id}" name="userId" hidden />
            <div class="form-group">
                <label for="isEnabled">Is Enabled:</label>
                <c:choose>
                    <c:when test = "${user.isEnabled}">
                        <input id="isEnabled" type="checkbox" name="isEnabled" checked />
                    </c:when>
                    <c:otherwise>
                        <input id="isEnabled" type="checkbox" name="isEnabled" />
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="form-group">
                <label for="isAdmin">Is Admin:</label>
                <c:choose>
                    <c:when test = "${user.isAdmin}">
                        <input id="isAdmin" type="checkbox" name="isAdmin" checked />
                    </c:when>
                    <c:otherwise>
                        <input id="isAdmin" type="checkbox" name="isAdmin" />
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
        <form id="updateForm" action="/usermanagement/updateUser" method="POST">
            <input type="hidden" name="userId" value="${user.id}" />
            <input type="hidden" name="isEnabled" id="isEnabledHidden" />
            <input type="hidden" name="isAdmin" id="isAdminHidden" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="submit" class="btn btn-default" value="submit">
        </form>
    </div>
</div>

<script type="text/javascript" src="<c:url value='/resources/js/userDetails.js'/>"></script>

