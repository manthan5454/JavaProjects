<jsp:include page="adminheader.jsp">
    <jsp:param name="title" value="Web Store"/>
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="menus" scope="session" value="${applicationScope.menus}"/>

<%--Page Inside Content here--%>
<div class="page-content p-5" id="content">

    <div class="container">
        <c:if test="${sessionScope.message != null}">
            <div class="alert alert-info">
                    ${sessionScope.message}
            </div>
        </c:if>
        <div class="row">
            <!-- Logo Table -->
            <div class="col-10 glass-overlay shadow mx-auto p-3">
                <h1 class="float-left">Logo Settings</h1>
                <!-- Button trigger Logo Setting modal -->
                <button type="button" class="float-right btn btn-primary" data-toggle="modal" data-target="#logoModal">
                    Add New
                </button>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Category</th>
                        <th scope="col">Location</th>
                        <th scope="col">Edit Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="sys" items="${SysSettingList}">
                        <tr>
                            <td>${sys.id}</td>
                            <td>${sys.key}</td>
                            <td><img src="${sys.value}" height="50px" width="50px"/></td>
                            <td>
                                <button type="button" class="btn btn-primary"><i class="fa fa-eye"></i></button>
                                <button type="button" class="btn btn-success logo-settings" data-id="${sys.id}"
                                        data-key="${sys.key}" data-src="${sys.value}"><i class="fa fa-edit"></i>
                                </button>
                                <button type="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Modal for Logo Setting-->
            <div class="modal" id="logoModal" tabindex="-1" role="dialog" aria-labelledby="logoModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <form action="logo-settings" method="post" target="_self" enctype="multipart/form-data">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="logoModalLabel">Logo Setting</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                                <div class="mb-3 logo-component">
                                    <label for="Logo">Logo : Upload Logo</label>
                                    <div class="custom-file">
                                        <div class="row">
                                            <div class="col-3">
                                                <img src="" style="display: none" height="50px" width="50px"/>
                                            </div>
                                            <div class="col-8">
                                                <input type="file" name="logo" class="custom-file-input" id="logo">
                                                <label class="custom-file-label" for="logo">Choose file for Logo</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3 favicon-component">
                                    <label for="favicon">Favicon : Upload Favicon</label>
                                    <div class="custom-file">
                                        <div class="row">
                                            <div class="col-3">
                                                <img src="" style="display: none" height="50px" width="50px"/>
                                            </div>
                                            <div class="col-8">
                                                <input type="file" name="favicon" class=" custom-file-input"
                                                       id="favicon">
                                                <label class="custom-file-label" for=favicon>Choose file for
                                                    Favicon</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-3">
                                </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" value="Save" name="submit" class="btn btn-primary">
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-10 glass-overlay shadow mx-auto p-3">
                <h1 class="float-left">Menu Settings</h1>
                <!-- Button trigger Menu Setting modal -->
                <button type="button" class="float-right btn btn-primary" data-toggle="modal" data-target="#menuModal">
                    Add New
                </button>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Link</th>
                        <th scope="col">Order</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="menu" items="${MenuSettingList}">
                        <tr>
                            <td>${menu.id}</td>
                            <td>${menu.menu_title}</td>
                            <td>${menu.menu_slug}</td>
                            <td>${menu.menu_order}</td>
<%--                            <script>--%>
<%--                                console.log(${menu.toString()})--%>
<%--                            </script>--%>
                            <td>
                                <button type="button" disabled class="btn btn-primary"><i class="fa fa-eye"></i>
                                </button>
                                <button type="button" class="btn btn-success menu-setting" data-toggle="modal" data-target="#menuModal"
                                        data-menu-order="${menu.menu_order}" data-menu-slug="${menu.menu_slug}"><i class="fa fa-edit"></i></button>
                                <button type="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Modal for Menu Setting-->
            <div class="modal" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="menuModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="menu-settings" method="post">
                            <div class="modal-header">
                                <h3 class="modal-title" id="menuModalLabel">Menu Link Setting</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="menu-title" class="form-label">Title</label>
                                    <input type="text" class="form-control" id="menu-title" name="menu-title">
                                </div>
                                <div class="mb-3">
                                    <label for="menu-slug" class="form-label">Url/Permalink</label>
                                    <select id="menu-slug" class="form-control" name="menu-slug">
                                        <c:forEach var="menu" items="${menus}">
                                            <option value="${menu}">${menu}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="menu-order" class="form-label">Order</label>
                                    <input type="number" min="0" class="form-control" name="menu-order" id="menu-order">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="submit" value="Save" name="submit" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-10 glass-overlay shadow mx-auto p-3">
                <h1 class="float-left">Carosal slider Settings</h1>
                <!-- Button trigger slider Setting modal -->
                <button type="button" class="float-right btn btn-primary" data-toggle="modal"
                        data-target="#sliderModal">
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
                                        data-target="#sliderModal"><i class="fa fa-edit"></i></button>
                                <button type="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Modal for slider Setting-->
            <div class="modal" id="sliderModal" tabindex="-1" role="dialog" aria-labelledby="sliderModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <form action="slider-settings" method="post"  enctype="multipart/form-data">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="sliderModalLabel">Carosal slider Link Setting</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">

                                <div class="mb-3">
                                    <label for="slider_title" class="form-label">Title</label>
                                    <input type="text" class="form-control" id="slider_title" name="slider_title">
                                </div>
                                <div class="mb-3">
                                    <label for="slider_link" class="form-label">Link</label>
                                    <input type="text" class="form-control" id="slider_link" name="slider_link">
                                </div>
                                <div class="mb-3">
                                    <label for="slider_description" class="form-label">Description</label>
                                    <input type="text" class="form-control" id="slider_description"
                                           name="slider_description">
                                </div>
                                <div class="mb-3">
                                    <label for="slider_order" class="form-label">Order</label>
                                    <input type="number" min="0" class="form-control" id="slider_order"
                                           name="slider_order">
                                </div>
                                <div class="mb-3">
                                    <label for="slider_image" class="form-label">Image</label>
                                    <div class="custom-file">
                                        <div class="col-3">
                                            <img src="" style="display: none" height="300px" width="300px"/>
                                        </div>
                                        <div class="col-8">
                                            <input type="file" name="slider_image" class="custom-file-input"
                                                   id="slider_image">
                                            <label class="custom-file-label" for="slider_image">Choose file for Carousal
                                                Slide</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <input type="submit" value="Save" name="submit" class="btn btn-primary">
                                </div>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-10 glass-overlay shadow mx-auto p-3">
                <h1 class="float-left">Product Category Settings</h1>
                <!-- Button trigger slider Setting modal -->
                <button type="button" class="float-right btn btn-primary" data-toggle="modal"
                        data-target="#productCategoryModal">
                    Add New
                </button>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Title</th>
                        <th scope="col">Link</th>
                        <th scope="col">Image</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ProCategory" items="${ProductCategoryList}">
                        <tr>
                            <td>${ProCategory.id}</td>
                            <td>${ProCategory.name}</td>
                            <td>${ProCategory.slug}</td>
                            <td>
                                <button type="button" class="btn btn-sm btn-primary view-image" data-toggle="modal"
                                        data-view-image="${ProCategory.image}" data-target="#viewImage">view
                                </button>
                            </td>
                            <td>
                                <form>
                                    <input type="hidden" name="id" value="${ProCategory.id}" pattern="[0-9]+">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-eye"></i></button>
                                    <button type="button" class="btn btn-success proCat-setting"
                                            data-procat-id="${ProCategory.id}"
                                            data-procat-name="${ProCategory.name}"
                                            data-procat-slug="${ProCategory.slug}"
                                            data-toggle="modal" data-target="#productCategoryModal"><i
                                            class="fa fa-edit ">
                                    </i></button>
                                    <button type="submit" class="btn btn-danger" formaction="delete-pro-cat"
                                            formmethod="post"><i class="fa fa-trash"></i></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Modal for Product Category Setting-->
            <div class="modal" id="productCategoryModal" tabindex="-1" role="dialog"
                 aria-labelledby="productCategoryModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <form action="pcategory-settings" method="post" enctype="multipart/form-data">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="productCategoryModalLabel">Carosal slider Link Setting</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="productcategoryname" class="form-label">Name</label>
                                    <input type="text" name="productcategoryname" class="form-control"
                                           id="productcategoryname">
                                </div>
                                <div class="mb-3">
                                    <label for="productcategoryslug" class="form-label">Url/Permalink</label>
                                    <input type="text" name="productcategoryslug" class="form-control"
                                           id="productcategoryslug">
                                </div>
                                <div class="mb-3">
                                    <label for="productcategoryimage" class="form-label">Product Category
                                        Image</label>
                                    <div class="custom-file">
                                        <input type="file" name="product_image" class="custom-file-input"
                                               id="productcategoryimage">
                                        <label class="custom-file-label" for="productCategoryimage">Choose Product
                                            Category File</label>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="submit" value="Save" name="submit" class="btn btn-primary">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%--            Modal for Image View--%>
            <div class="modal fade view-image" id="viewImage" tabindex="-1" role="dialog"
                 aria-labelledby="myHugeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-body">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <div class="container get-view-description"></div>
                            <img class="get-view-image img-fluid" size=""/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<jsp:include page="adminfooter.jsp"></jsp:include>