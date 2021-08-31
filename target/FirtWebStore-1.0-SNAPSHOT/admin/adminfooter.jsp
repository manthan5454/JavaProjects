<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script> -->

<script>
    $(document).ready(function () {
        $("h1,h2,h3,h4,h5,p ").addClass("font-weight-light ");
        $("a.btn").addClass("border-0 rounded-0 text-decoration-none");
        $(".nav-link").addClass("w-100 ");
        $("#sidebarbtn").click(function () {
            $("#sidepan").toggleClass("d-none d-block d-sm-block d-md-block");
        });
        $('.custom-file-input').on('change', function () {
            //get the file name
            var fileName = $(this).val();
            //replace the "Choose a file" label
            $(this).next('.custom-file-label').html(fileName);
        })
        $("#sidepan").hover(function () {
            $(".sidebar").toggleClass("d-inline");
            $("#sidepan").toggleClass("col-lg-1 col-xl-1 col-lg-2 col-xl-2");
            $("#mainSide").toggleClass("col-lg-11 col-xl-11 col-lg-10 col-xl-10")
        })
        $('.logo-settings').click(function () {
            let id = $(this).data('id'), key = $(this).data('key'), src = $(this).data('src');
            let logomodal = $('#logoModal');
            $('[class*="-component"] img').hide();
            $('#favicon, #logo').removeAttr('disabled');
            if (key === 'logo') {
                $('.logo-component img').attr('src', src);
                $('.logo-component img').show();
                $('#favicon').attr('disabled', 'true');
            } else {
                $('.favicon-component img').attr('src', src);
                $('.favicon-component img').show();
                $('#logo').attr('disabled', 'true');
            }
            logomodal.modal('show');
        });
        $('.menu-setting').click(function () {
            let slug = '/' + $(this).data('menu-slug'), order = $(this).data('menu-order')
            $("option[value='" + slug + "']").attr('selected', true);
            $("#menu-order").val(order);
            // $('#menuModal').modal('show');
        });
        $('.view-image').click(function () {
            let src = $(this).data('view-image');
            $(".get-view-description").empty();
            $(".get-view-image").attr('src', src);
        });
        $(".view-description").click(function () {
            let description = $(this).data('view-description');
            $(".get-view-image").attr('src', "");
            $(".get-view-description").text(description);
        });
        $(".slider-setting").click(function () {
            let id = $(this).data('slider-id'), link = $(this).data('slider-link'),
                title = $(this).data('slider-title'),
                description = $(this).data('slider-description'), img = $(this).data('slider-image'),
                order = $(this).data('slider-order');
            $("#slider_id").val(id)
            $("#slider_title").val(title).addClass("disabled")
            $("#slider_link").val(link)
            $("#slider_description").val(description)
            $("#slider_order").val(order)
            $("#sliderModal img").attr('src', img)
            // $('#sliderModal').modal('show');
        });
        $(".proCat-setting").click(function () {
            let id = $(this).data('procat-id'), slug = $(this).data('procat-slug'),
                title = $(this).data('procat-title'),
                description = $(this).data('slider-description'), img = $(this).data('slider-image'),
                order = $(this).data('slider-order');
            $("#productcategoryname").val(title)
            $("#productcategoryslug").val(slug)
            // $('#sliderModal').modal('show');
            var url = $('img').attr('src');
            console.log(url);
            $('#productcategoryimage').val(img);
        });
        let rowCount = 0;
        // $("#product_form").submit(function (event) {
        //     event.preventDefault();
        //     let size="",stock="",price="",color="";
        //     $(".product-size").each(function (){
        //         size = size+$(this).val()+",";
        //     })
        //     $("input[name*=product_size]").val(size.slice(0,-1));   //slice is use for remove custom "," operator
        //     $(".product-price").each(function (){
        //         price = price+$(this).val()+",";
        //     })
        //     $("input[name*=product_price]").val(price.slice(0,-1)); //slice is use for remove custom "," operator
        //     $(".product-color").each(function (){
        //         color = color+$(this).val()+",";
        //     })
        //     $("input[name*=product_color]").val(color.slice(0,-1)); //slice is use for remove custom "," operator
        //     $(".product-stock").each(function (){
        //         stock = stock+$(this).val()+",";
        //     })
        //     $("input[name*=product_stock]").val(stock.slice(0,-1));  //slice is use for remove custom "," operator
        //     event.currentTarget.submit();
        // });
        $("#addrowbtn").click(function () {
            var size = $('#product_size').val();
            var price = $('#product_price').val();
            var color = $('#product_color').val();
            var stock = $('#product_stock').val();
            if (size != "" && price != "" && color != "" && stock != "") {
                $("#product_table tbody").append('<tr class="rowCount'+rowCount+'">' +
                    '<td><input readonly name="product_size[]" class="form-control product-size rowCount'+rowCount+'" type="text" value='+size+'></td>' +
                    '<td><input readonly name="product_price[]" class="form-control product-price rowCount'+rowCount+'" type="number" value='+price+'></td>' +
                    '<td><input readonly name="product_color[]" class="form-control product-color rowCount'+rowCount+'" type="text" value='+color+'></td>' +
                    '<td><input readonly name="product_stock[]" class="form-control product-stock rowCount'+rowCount+'" type="number" value='+stock+'></td>' +
                    '<td><button class="btn btn-danger btn-sm deleterow">X</button></td>' +
                    '</tr>');
                rowCount++;
                $("#product_stock,#product_color,#product_price,#product_size").val('');
            } else {
                alert("Pls Fill All Detail");
            }
            $(".deleterow").click(function (){
                $(this).closest('tr').remove;
            })
        })
    })
</script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load("current", {
        packages: ["corechart"]
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Task', 'Hours per Day'],
            ['Work', 11],
            ['Eat', 2],
            ['Commute', 2],
            ['Watch TV', 2],
            ['Sleep', 7]
        ]);

        var options = {
            title: 'Product Sale',
            titleTextStyle: {
                color: 'Black',
                fontSize: 16,
                alignment: 'center'
            },
            chartArea: {
                left: 50,
                top: 40,
                bottom: 10,
                width: '90%',
                height: '90%'
            },
            pieHole: 0.5,
            backgroundColor: {
                'fill': '#fff',
                'fillOpacity': 0
            },
            backgroundColor: {
                'fill': '#000',
                'fillOpacity': 0
            },
            legend: {
                textStyle: {
                    color: 'black',
                    fontSize: 14,
                }
            },
            pieSliceBorderColor: 'none',
            width: 'auto',

        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
    }
</script>

<script type="text/javascript">
    google.charts.load('current', {
        'packages': ['corechart']
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Year', 'Sales', 'Expenses'],
            ['2004', 1000, 400],
            ['2005', 1170, 460],
            ['2006', 660, 1120],
            ['2007', 1030, 540]
        ]);
        var options = {
            title: 'Company Performance',
            titleTextStyle: {
                color: 'white',
                fontSize: 16,
                alignment: 'center'
            },
            curveType: 'function',
            chartArea: {
                left: 40,
                top: 45,
                width: '70%',
                height: '75%',
            },
            vAxis: {
                baselineColor: '#C0C0C0',
            },
            // backgroundColor: {
            //     'fill': '#fffff',
            //     'fillOpacity': 0.4
            // },
            backgroundColor: {
                'fill': '#fff',
                'fillOpacity': 0
            },
            legend: {
                textStyle: {
                    color: 'black',
                },
                alignment: 'center',
            },
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart'));

        chart.draw(data, options);
    }
</script>

</html>