<%@ page import="com.ex.FirstWebStore.ContactUs.MessageTypes" %>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Web Store" />
</jsp:include>

<%--success = Your Details Saved Successfully, We reach you ASAP.--%>
<%--error = Error While Saving Info, Contact Us using Phone/Email--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<button  class="btn btn-success btn-lg px-3"><a href="contactList"> GoTO List</a></button>

<!-- Start Contact -->
<div class="container py-5">
    <% if(session.getAttribute(MessageTypes.SUCCESS.toString()) != null){ %>
    <div class="alert alert-success">
        <p><%=session.getAttribute(MessageTypes.SUCCESS.toString())%></p>
    </div>
    <%session.removeAttribute(MessageTypes.SUCCESS.toString());%>
    <%}else if(session.getAttribute(MessageTypes.ERROR.toString()) != null){ %>
    <div class="alert alert-danger <%=session.getAttribute(MessageTypes.ERROR.toString())%>">
        <p><%=session.getAttribute(MessageTypes.ERROR.toString())%></p>
    </div>
    <%session.removeAttribute(MessageTypes.ERROR.toString());%>
    <% }%>
    <div class="row py-5">
        <form class="col-md-9 m-auto" action="insertContact" method="post" role="form">
            <div class="row">
                <div class="form-group col-md-6 mb-3">
                    <label for="name">Name</label>
                    <input type="text" class="form-control mt-1" id="name" name="name" placeholder="Name">
                </div>
                <div class="form-group col-md-6 mb-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control mt-1" id="email" name="email" placeholder="Email">
                </div>
            </div>
            <div class="mb-3">
                <label for="subject">Subject</label>
                <input type="text" class="form-control mt-1" id="subject" name="subject" placeholder="Subject">
            </div>
            <div class="mb-3">
                <label for="message">Message</label>
                <textarea class="form-control mt-1" id="message" name="message" placeholder="Message" rows="8"></textarea>
            </div>
            <div class="row">
                <div class="col text-end mt-2">
                    <button type="submit" value="contactSubmit" class="btn btn-success btn-lg px-3">Let’s Talk</button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- End Contact -->

<%@include file="footer.jsp"%>
