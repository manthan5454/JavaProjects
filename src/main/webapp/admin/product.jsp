<jsp:include page="adminheader.jsp">
    <jsp:param name="title" value="Web Store"/>
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Page Inside Content here--%>
<div class="page-content p-5" id="content">
    <h1>Product</h1>
    <c:if test="${sessionScope.message != null}">
        <div class="alert alert-info">
                ${sessionScope.message}
        </div>
    </c:if>
    <div class="row">
        <div class="col-10 glass-overlay shadow mx-auto p-3">
            <h1 class="float-left">Product List</h1>
            <!-- Button trigger slider Setting modal -->
            <button type="button" class="float-right btn btn-primary" data-toggle="modal"
                    data-target="#addProductModal">
                Add New
            </button>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Title</th>
                    <th scope="col">Link</th>
                    <th scope="col">Order</th>
                    <th scope="col">Description</th>
                    <th scope="col">Image</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="slider" items="${SliderSettingList}">
                    <tr>
                        <td>${slider.id}</td>
                        <td>${slider.slider_title}</td>
                        <td>${slider.slider_link}</td>
                        <td>${slider.slider_order}</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-primary view-description"
                                    data-toggle="modal"
                                    data-view-description="${slider.slider_description}" data-target="#viewImage">
                                view
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-sm btn-primary view-image" data-toggle="modal"
                                    data-view-image="${slider.slider_image}" data-target="#viewImage">view
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary"><i class="fa fa-eye"></i></button>
                            <button type="button" class="btn btn-success slider-setting"
                                    data-slider-id="${slider.id}"
                                    data-slider-title="${slider.slider_title}"
                                    data-slider-link="${slider.slider_link}"
                                    data-slider-order="${slider.slider_order}"
                                    data-slider-description="${slider.slider_description}"
                                    data-slider-image="${slider.slider_image}" data-toggle="modal"
                                    data-target="#addProductModal"><i class="fa fa-edit"></i></button>
                            <button type="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- Modal for add Product-->
        <div class="modal fade" id="addProductModal" tabindex="-1" role="dialog" aria-labelledby="addProductLabel"
             aria-modal="true">
            <div class="modal-dialog modal-xl">
                <form id="product_form" action="add-product" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="add-product">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-4">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Brand</span>
                                        </div>
                                        <input type="text" class="form-control" placeholder="Brand name"
                                               id="product_brand" name="product_brand" aria-label="Username"
                                               aria-describedby="basic-addon1">
                                    </div>

                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">Name</span>
                                        </div>
                                        <input type="text" class="form-control" id="product_name" name="product_name"
                                               placeholder="Product name" aria-label="Username"
                                               aria-describedby="basic-addon1">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">SKU</span>
                                        </div>
                                        <input type="text" class="form-control" id="product_sku" name="product_sku"
                                               placeholder="Product Desgine Code" aria-label="sku"
                                               aria-describedby="basic-addon1">
                                    </div>
                                    <%--                                    <div class="input-group mb-3">--%>
                                    <%--                                        <input type="text" class="form-control" id="product_slug" name="product_slug" readonly placeholder="Auto generate Slug" aria-label="slug" aria-describedby="basic-addon2">--%>
                                    <%--                                    </div>--%>
                                </div>
                                <div class="col-4">
                                    <div class="form-group">
                                        <label for="product_description">Product Description</label>
                                        <textarea class="form-control" id="product_description"
                                                  name="product_description" rows="4"></textarea>
                                    </div>
                                </div>
                                <div class="col-3"></div>
                            </div>
                            <div class="row">
                                <table class="table m-2" id="product_table">
                                    <thead>
                                    <tr>
                                        <th>
                                            <div class="input-group ">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Size</span>
                                                </div>
                                                <input type="text" class="form-control" id="product_size"
                                                       placeholder="Size" aria-label="size">
<%--                                                <input hidden type="text" class="form-control"--%>
<%--                                                       name="product_size[]" placeholder="Size" aria-label="size">--%>
                                            </div>
                                        </th>
                                        <th>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Price</span>
                                                </div>
                                                <input type="number" class="form-control" id="product_price"
                                                       min="0" placeholder="Price"
                                                       aria-label="price">
<%--                                                <input hidden type="number" class="form-control"--%>
<%--                                                       name="product_price[]" min="0" placeholder="Price"--%>
<%--                                                       aria-label="price">--%>
                                            </div>
                                        </th>
                                        <th>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Color</span>
                                                </div>
                                                <input type="text" class="form-control" id="product_color"
                                                       placeholder="Color" aria-label="color">
<%--                                                <input hidden type="text" class="form-control"--%>
<%--                                                       name="product_color[]" placeholder="Color" aria-label="color">--%>
                                            </div>
                                        </th>
                                        <th>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">Stock</span>
                                                </div>
                                                <input type="number" class="form-control" id="product_stock"
                                                       placeholder="Stock" min="0"
                                                       aria-label="stock">
<%--                                                <input hidden type="number" class="form-control"--%>
<%--                                                       name="product_stock[]" placeholder="Stock" min="0"--%>
<%--                                                       aria-label="stock">--%>
                                            </div>
                                        </th>
                                        <th>
                                            <button type="button" class="btn btn-primary" id="addrowbtn">
                                                Add
                                            </button>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" value="submit" id="submitProduct" class="btn btn-primary">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br>


    <br>
    <%--    <div class="col-md-12 wow fadeInLeft">--%>
    <%--        <table class="table">--%>
    <%--            <thead>--%>
    <%--            <tr>--%>
    <%--                <th>Id</th>--%>
    <%--                <th>Name</th>--%>
    <%--                <th>Email</th>--%>
    <%--                <th>Subject</th>--%>
    <%--                <th>Message</th>--%>
    <%--                <th>Status</th>--%>
    <%--            </tr>--%>
    <%--            </thead>--%>
    <%--            <tbody>--%>
    <%--            <c:forEach var="con" items="${ContactUsArrayList}">--%>
    <%--                <tr>--%>
    <%--                    <td><c:out value="${con.id}"/></td>--%>
    <%--                    <td><c:out value="${con.name}"/></td>--%>
    <%--                    <td><c:out value="${con.email}"/></td>--%>
    <%--                    <td><c:out value="${con.subject}"/></td>--%>
    <%--                    <td><c:out value="${con.message}"/></td>--%>
    <%--                    <td><c:out value="${con.status}"/></td>--%>
    <%--                </tr>--%>
    <%--            </c:forEach>--%>
    <%--            </tbody>--%>
    <%--        </table>--%>
    <%--        <p>Today's date: <%= (new java.util.Date())%>--%>
    <%--        </p>--%>
    <%--    </div>--%>
</div>

</body>
<jsp:include page="adminfooter.jsp"></jsp:include>
