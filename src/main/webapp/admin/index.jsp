<jsp:include page="adminheader.jsp">
    <jsp:param name="title" value="Web Store" />
</jsp:include>

<%--Page Inside Content here--%>
        <div class="container-fluid p-0 m-0 ">
            <div class="row m-0 px-3">
                <div class="col-md-5 col-11 p-0 mt-3 mx-auto">
                    <div class="glass-overlay shadow w-100  rounded p-0 m-0">
                        <div id="chart" class=" p-0 m-0">
                        </div>
                    </div>
                </div>
                <div class=" col-md-5 col-11 p-0 mt-3 mx-auto">
                    <div class="glass-overlay shadow w-100  rounded p-0 m-0">
                        <div id="donutchart" class=" m-0 p-0"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row m-0 mt-3">
            <div class="col-md-4 col-12 mb-3 w-100">
                <div class="card shadow border-0 glass-overlay">
                    <a class="row text-decoration-none " href="order.html#order">
                        <div class="col-5 text-center">
                            <i class="fa fa-shopping-bag fa-5x p-2"></i>
                        </div>
                        <div class="col-6 p-0 w-100">
                            <div class="card-body p-0">
                                <h4 class="lead text-right my-3"> Order</h4>
                                <p class="card-text text-right my-2">Pending: <span
                                        class="font-weight-bold text-right lead">15</span> </p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 col-12 mb-3 w-100">
                <div class="card shadow border-0 glass-overlay ">
                    <a class="row text-decoration-none" href="order.html#ordertransist">
                        <div class="col-5 text-center">
                            <i class="fa fa-truck fa-5x p-2"></i>
                        </div>
                        <div class="col-6 p-0 w-100">
                            <div class="card-body p-0">
                                <h4 class="lead text-right my-3">In Transist</h4>
                                <p class="card-text text-right my-2">Shipped: <span
                                        class="font-weight-bold text-right lead">14</span> </p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 col-12 mb-3 w-100">
                <div class="card shadow border-0 glass-overlay">
                    <a class="row text-decoration-none" href="order.html#orderhistory">
                        <div class="col-5 text-center">
                            <i class="fa fa-clock-o fa-5x p-2"></i>
                        </div>
                        <div class="col-6 p-0 w-100">
                            <div class="card-body p-0">
                                <h4 class="lead text-right my-3"> Order History</h4>
                                <p class="card-text text-right my-2">Orderd: <span
                                        class="font-weight-bold text-right lead">1235</span> </p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row flex-grow-1 m-0 ">

        </div>



<jsp:include page="adminfooter.jsp"></jsp:include>