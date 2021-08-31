
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Web Store" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br>
<div class="col-md-12 wow fadeInLeft">
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Subject</th>
            <th>Message</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="con" items="${ContactUsArrayList}">
            <tr>
                <td><c:out value="${con.id}"/></td>
                <td><c:out value="${con.name}"/></td>
                <td><c:out value="${con.email}"/></td>
                <td><c:out value="${con.subject}"/></td>
                <td><c:out value="${con.message}"/></td>
                <td><c:out value="${con.status}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>Today's date: <%= (new java.util.Date())%></p>
</div>


<%@include file="footer.jsp"%>