<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rigatron.rigs4j.web.models.UserVM, com.rigatron.rigs4j.web.models.UserRoleVM" %>

<div class="row">
    <div class="col-xs-4 col-xs-offset-2">
        <table class="table table-striped">
            <thead>
                <tr>
                  <th>Username</th>
                  <th class="date-column">Create Date</th>
                  <th class="date-column">Last Modified</th>
                  <th>Roles</th>
                  <th class="is-enabled-column">Is Enabled</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><a href="/usermanagement/userDetails?userId=${user.id}">${user.username}</a></td>
                        <td>${user.createDate}</td>
                        <td>${user.lastModifiedDate}</td>
                        <td>${user.roleString}</td>
                        <c:choose>
                            <c:when test = "${user.isEnabled}">
                                <td><span class="glyphicon glyphicon-ok user-enabled-glyphicon"></span></td>
                            </c:when>
                            <c:otherwise>
                                <td><span class="glyphicon glyphicon-remove user-disabled-glyphicon"></span></td>
                            </c:otherwise>
                        </c:choose>
                        <td></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>