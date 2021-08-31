<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://unpkg.com/css-doodle@0.19.1/css-doodle.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="admin/assets/bootstrap.min.css">

    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <%--    <!-- jQuery library -->--%>
    <%--    <script src="admin/assets/jquery.min.js"></script>--%>

    <%--    <!-- Popper JS -->--%>
    <%--    <script src="admin/assets/popper.min.js"></script>--%>

    <%--    <!-- Latest compiled JavaScript -->--%>
    <%--    <script src="admin/assets/bootstrap.min.js"></script>--%>

    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="admin/assets/customCSS.css">
    <style>
        #chart,
        #donutchart {
            min-height: 290px;
            max-height: 300px;
        }
        body { margin: 0; }
        css-doodle.doodle {
            position: fixed;
            height: 100vh;
            width: 100vw;
        }
    </style>
</head>

<body class="container-fluid p-0 m-0">
<css-doodle class="doodle " click-to-update>
    <style>
        :doodle {
            @grid: 8 / 90%;
            @shape: rectangle;
            @size: 100vmax;
        }

        transition: .2s @r(.6s);
        border-radius: @pick(100% 0, 0 100%);

        will-change: transform;
        transform: scale(@r(.25, 1.25));

        background: hsla(
        calc(240 - 6 * @x * @y),
        70%, 68%, @r.8
        );
    </style>
</css-doodle>
<div class="doodle-container">

</div>
<div class="vh-100">
    <div class="row fixe m-0 w-100 h-100 justify-content-center">
        <div class="col-lg-1 col-xl-1 float-left glass-overlay shadow-sm d-none d-sm-none d-md-none d-lg-block p-0" id="sidepan">
            <div class="h-100 w-100 d-flex flex-column">
                <div class="row m-0 p-0 justify-content-center ">
                    <h4 class="w-100">   <a class="nav-link btn w-100" href="home">
                        Admin</a></h4>

                </div>
                <div class="row m-0 justify-content-center hover">
                    <a class="nav-link m-0" href="admin"><i class="fa fa-area-chart fa-2x" aria-hidden="true"></i>
                        <span class="sidebar">Dashboard</span><span class="badge badge-danger float-right">14</span></a>
                    <a class="nav-link m-0" href="productlist"><i class="fa fa-briefcase fa-2x" aria-hidden="true"></i>
                        <span class="sidebar"> Product </span><span class="badge badge-danger float-right">2</span></a>
                    <div class="nav-item dropdown dropright p-0 m-0 w-100">
                        <a class="nav-link m-0" href="" data-toggle="dropdown"><i
                                class="fa fa-shopping-bag  fa-2x" aria-hidden="true"></i>
                            <span class="sidebar"> Order</span> <span
                                    class="badge badge-danger float-right">15</span></a>
                        <div class="dropdown-menu dark-overlay dropdown-menu-right mx-1">
                            <a href="" class=" dropdown-item pl-3"><i
                                    class="fa fa-list-ul fa-2x pr-2"></i>Order List</a>
                            <a href="" class=" dropdown-item pl-3"> <i
                                    class="fa fa-truck fa-2x pr-2"></i>In Transist</a>
                            <a href="" class=" dropdown-item pl-3"> <i
                                    class="fa fa-clock-o fa-2x pr-2"></i>History</a>
                        </div>
                    </div>

                    <a class="nav-link" href="contact-inquiry"><i class="fa m-auto fa-2x fa-envelope" aria-hidden="true"> </i>
                        <span class="sidebar">Contact Inquiry</span> <span class="badge badge-danger float-right">3</span></a>

                    <a class="nav-link" href="site-settings"><i class="fa  fa-2x  fa-gears" aria-hidden="true">
                    </i><span class="sidebar"> Site Settings</span> <span class="badge badge-danger float-right">23</span></a>

                    <a class="nav-link" href=""><i class="fa  fa-2x  fa-angle-double-right " aria-hidden="true"> </i>
                        <span class="sidebar"> More...</span> <span class="badge badge-danger float-right">3</span></a>
                </div>

<%--                <div class="row p-0 m-0 flex-grow-1 d-none d-sm-none d-md-none d-lg-block"></div>--%>

<%--                <div class="row p-0 m-0 justify-content-center ">--%>
<%--                    <a href="signin.html" id="signout" class="nav-link"><i class=" fa fa-2x  fa-sign-out  text-danger" aria-hidden=" true "></i><span class="sidebar"> Logout</span></a>--%>
<%--                </div>--%>
            </div>
        </div>


        <!-- Main Side -->

        <%--        top navbar--%>
        <div class="col-xl-11 col-lg-11 col-md-12 col-sm-12 col-xs-12 m-0 p-0 " id="mainSide">
            <div class=" d-flex flex-column w-100 ">
                <div class="row glass-overlay shadow-sm p-0 m-0 ">
                    <h4 class="flex-grow-1 my-2 pl-3 textHD align-text-bottom ">Dashboard</h4>
                    <div class="btn-group " role="group ">
                        <!-- notification -->
                        <div class="nav-item dropdown">
                            <a class="nav-link iconHD h-100" href="#" id="navbardrop_notification"
                               data-toggle="dropdown"><i
                                    class=" fa fa-bell-o px-1 " aria-hidden="true "></i></a>
                            <div class="dropdown-menu dark-overlay mt-2 dropdown-menu-right justify-content-start">
                                <div class="d-flex">
                                        <span class="dropdown-header font-weight-bold flex-grow-1  ">
                                            Notification
                                        </span>
                                    <button class="btn small text-danger">Clear All <i class=" fa fa-close px-1 "
                                                                                       aria-hidden="true "></i></button>
                                </div>
                                <a href="" class=" dropdown-item ">Profile picture update succesfully</a>
                                <a href="" class=" dropdown-item ">receive new upadte about version</a>
                                <a href="" class=" dropdown-item ">make secure Setting of password</a>

                                <a href="" class=" dropdown-item ">upadte password old is expired</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link iconHD h-100" href="#" id="navbardrop_mail" data-toggle="dropdown"><i
                                    class=" fa fa-envelope px-1 iconHD " aria-hidden=" true "></i></a>
                            <div class="dropdown-menu dark-overlay mt-2 dropdown-menu-right">
                                <a href="#profile " class=" dropdown-item ">Profile</a>
                                <a href="#activity" class=" dropdown-item ">Activity</a>
                                <a href="#setting " class=" dropdown-item ">Setting</a>
                                <a href="signin.html" class=" dropdown-item ">Logout</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link iconHD h-100" href="#" id="navbardrop_menu" data-toggle="dropdown">Admin
                                bhai
                                <i class=" fa fa-angle-down px-1" aria-hidden="true "></i> </a>
                            <div class="dropdown-menu dark-overlay mt-2 dropdown-menu-right m-2">
                                <a href="#profile " class=" dropdown-item "><i class=" fa fa-user px-2 "
                                                                               aria-hidden="true "></i>Profile</a>
                                <a href="#activity" class=" dropdown-item "><i class=" fa fa-bolt px-2 "
                                                                               aria-hidden=" true "></i>Activity</a>
                                <a href="#setting " class=" dropdown-item "><i class=" fa fa-gear px-2 "
                                                                               aria-hidden=" true "></i>Setting</a>
                                <a href="signin.html" class=" dropdown-item "><i
                                        class=" fa fa-sign-out text-danger px-2" aria-hidden=" true "></i>Logout</a>
                            </div>
                        </div>
                        <button type="button" class="btn d-xl-none d-lg-none d-xl-none mr-1" id="sidebarbtn"><i
                                class=" fa fa-bars " aria-hidden=" true "></i></button>
                    </div>
                </div>
