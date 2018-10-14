<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.rigatron.rigs4j.web.models.User, com.rigatron.rigs4j.web.models.UserRole" %>

<div class="row">
    <div class="col-xs-4" style="margin-left: 50px">
        <table>
            <thead>
                <tr>
                  <th>Username</th>
                  <th>Create Date</th>
                  <th>Last Modified</th>
                  <th>Is Enabled</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.createDate}</td>
                        <td>${user.lastModifiedDate}</td>
                        <td></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>