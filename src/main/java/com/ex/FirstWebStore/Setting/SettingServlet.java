package com.ex.FirstWebStore.Setting;

import com.ex.FirstWebStore.ContactUs.ContactUs;
import com.ex.FirstWebStore.ContactUs.ContactUsDao;
import com.ex.FirstWebStore.ContactUs.MessageTypes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;


@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "SettingServlet", urlPatterns = {"/admin", "/site-settings", "/logo-settings", "/menu-settings", "/slider-settings", "/pcategory-settings", "/delete-pro-cat", "/contact-inquiry", "/add-product", "/productlist"})
public class SettingServlet extends HttpServlet {
    String filePath = "";
    HashMap<String, String[]> extensionList;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("menus", new String[]{"/home", "/shop", "/contact", "/about", "/shop-single"});
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        extensionList = new HashMap<>();
        extensionList.put("logo", new String[]{"jpg", "png", "jpeg"});
        extensionList.put("favicon", new String[]{"jpg", "ico", "png", "jpeg"});
        extensionList.put("slider", new String[]{"jpg", "png", "jpeg"});

        try {
            String path = request.getServletPath();
            switch (path) {
                case "/logo-settings":
                    logoSettings(request, response);
                    break;
                case "/menu-settings":
                    menuSettings(request, response);
                    break;
                case "/slider-settings":
                    sliderSettings(request, response);
                    break;
                case "/pcategory-settings":
                    pcategorysitesettings(request, response);
                    break;
                case "/delete-pro-cat":
                    deleteProCat(request, response);
                    break;
                case "/add-product":
                    addProduct(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Redirect Message" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getServletPath();
            switch (path) {
                case "/admin":
                    admin(request, response);
                    break;
                case "/site-settings":

                    sitesetting(request, response);
                    break;
                case "/contact-inquiry":
                    contactInquiry(request, response);
                    break;
                case "/productlist":
                    productlist(request, response);
                    break;
                default:
                    break;

            }
        } catch (Exception e) {
            System.out.println("Redirect Message" + e.getMessage());
        }
    }

    private void productlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin/product.jsp").forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
        Product product = new Product();
        ProductDao productDao = new ProductDao();
        PrintWriter out = response.getWriter();
        out.println("GetParameter: " + request.getParameter("product_brand"));
        out.println("GetParameter: " + request.getParameterValues("product_size[]").length);
//        response.setContentType("text/html");
        String brand = request.getParameter("product_brand");
        String name = request.getParameter("product_name");
        String sku = request.getParameter("product_sku");
        String description = request.getParameter("product_description");
        String[] sizes = request.getParameterValues("product_size[]");
        String[] prices = request.getParameterValues("product_price[]");
        String[] colors = request.getParameterValues("product_color[]");
        String[] stocks = request.getParameterValues("product_stock[]");
        product.setBrand(brand);
        product.setName(name);
        product.setSku(sku);
        product.setStock(Arrays.toString(stocks));
        product.setColor(Arrays.toString(colors));
        product.setSlug(sku);
        product.setDescription(description);
        product.setSize(Arrays.toString(sizes));
        product.setPrice(Arrays.toString(prices));
        out.println(productDao.checkProduct(sku));
        if(productDao.checkProduct(sku)){                   //Product Group Exist
            product.setGroup_id(1);
            for (int i = 0; i < sizes.length ; i++) {
                product.setSize(sizes[i]);
                product.setPrice(prices[i]);
                product.setColor(colors[i]);
                product.setStock(stocks[i]);
                product.setSku(sku,sizes[i],colors[i]);
                if(productDao.checkProduct(sku)){
                    out.println();//Add New Product
                    out.println(productDao.setProduct(product));
                    productDao.setProduct(product);
                }else{                                      //product Exist
                    out.println("Product Exist");
                }
            }
        }else{                                             //Product Group Create
            product.setGroup_id(0);
            productDao.setProduct(product);
        }
            HttpSession session = request.getSession();
            session.setAttribute("message", "Product Already Exist");
            response.sendRedirect("http://localhost:8080/FirtWebStore/productlist");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void addProductImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            Product product;
            ProductDao productDao = new ProductDao();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // Set factory constraints
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File("d:\\temp"));
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Set overall request size constraint
            upload.setSizeMax(1024 * 1024);
            PrintWriter out = response.getWriter();
            try {
                product = new Product();
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        out.println(name + "............" + value + "........");
//                        switch (name) {
//                            case "product_brand":
//                                product.setBrand(value);
//                                break;
//                            case "product_sku":
//                                product.setSku(value);
//                                break;
//                            case "product_name":
//                                product.setName(value);
//                                break;
//                            case "product_stock":
//                                product.setStock(value);
//                                break;
//                            case "product_color":
//                                product.setColor(value);
//                                break;
//                            case "product_size":
//                                product.setSize(value);
//                                break;
//                            case "product_price":
//                                product.setPrice(value);
//                                break;
//                            case "product_description":
//                                product.setDescription(value);
//                                break;
//                        }
                        out.println(product);
//                        productDao.setProduct(product);
//                        sliderSettingDao.setSliderSettings(slider);
                    } else {
//                        String fieldName = item.getFieldName(); //slider_title
//                        String fileName = item.getName(); //filename
//                        String contentType = item.getContentType().toLowerCase();
//                        out.println(fileName + "........" + contentType + "fieldName" + fieldName);
//                        try {
//                            if (contentType.contains("image")) {
//                                fileName = slider.slider_title + "." + fileName.split("[.]")[1];
//                                filePath = "C:\\Users\\manth\\IdeaProjects\\FirtWebStore\\src\\main\\webapp\\assets\\img\\" + fileName;
//                                File file = new File(filePath);
//                                item.write(file);
//                                slider.setSlider_image("./assets/img/" + fileName);
//                            } else {
//                                out.println("Wrong File Formate");
//                            }
//                        } catch (Exception e) {
//                            out.print(e);
//                        }
                    }
                }
//                ProductDao;
//                response.sendRedirect("http://localhost:8080/FirtWebStore/site-settings");
            } catch (Exception e) {
                System.out.println("error" + e.getMessage());
            }
//            response.sendRedirect("http://localhost:8080/FirtWebStore/admin");
        } catch (Exception e) {

        }
    }



    private void sliderSettings(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, FileUploadException {
        SliderSetting slider;
        SliderSettingDao sliderSettingDao = new SliderSettingDao();
        DiskFileItemFactory factory = new DiskFileItemFactory();
// Set factory constraints
        factory.setSizeThreshold(1024 * 1024);
        factory.setRepository(new File("d:\\temp"));
// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
// Set overall request size constraint
        upload.setSizeMax(1024 * 1024);
        PrintWriter out = response.getWriter();
        try {
            slider = new SliderSetting();
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    out.println(name + "............" + value + "........" + item.getContentType());
                    switch (name) {
                        case "slider_title":
                            slider.setSlider_title(value);
                            break;
                        case "slider_link":
                            slider.setSlider_link(value);
                            break;
                        case "slider_description":
                            slider.setSlider_description(value);
                            break;
                        case "slider_order":
                            slider.setSlider_order(Integer.parseInt(value));
                            break;
                    }
                    out.println(slider);
                    sliderSettingDao.setSliderSettings(slider);
                } else {
                    String fieldName = item.getFieldName(); //slider_title
                    String fileName = item.getName(); //filename
                    String contentType = item.getContentType().toLowerCase();
                    out.println(fileName + "........" + contentType + "fieldName" + fieldName);
                    try {
                        if (contentType.contains("image")) {
                            fileName = slider.slider_title + "." + fileName.split("[.]")[1];
                            filePath = "C:\\Users\\manth\\IdeaProjects\\FirtWebStore\\src\\main\\webapp\\assets\\img\\" + fileName;
                            File file = new File(filePath);
                            item.write(file);
                            slider.setSlider_image("./assets/img/" + fileName);
                        } else {
                            out.println("Wrong File Formate");
                        }
                    } catch (Exception e) {
                        out.print(e);
                    }
                }
            }
            sliderSettingDao.setSliderSettings(slider);
            response.sendRedirect("http://localhost:8080/FirtWebStore/site-settings");
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

    private void logoSettings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SysSetting sysSetting;
        SysSettingDao sysSettingDao = new SysSettingDao();
        DiskFileItemFactory factory = new DiskFileItemFactory();
// Set factory constraints
        factory.setSizeThreshold(1024 * 1024);
        factory.setRepository(new File("d:\\temp"));
// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
// Set overall request size constraint
        upload.setSizeMax(1024 * 1024);
        PrintWriter out = response.getWriter();
        try {
            sysSetting = new SysSetting();
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    out.println("Error happend");
                } else {
                    String fieldName = item.getFieldName(); //slider_title
                    String fileName = item.getName(); //filename
                    String contentType = item.getContentType().toLowerCase();

                    out.println("filename" + fileName + "........" + contentType + "fieldName" + fieldName);

                    try {
                        if (contentType.contains("image")) {
                            sysSetting.setKey(fieldName);
                            fileName = fieldName + "." + fileName.split("[.]")[1];
                            filePath = "C:\\Users\\manth\\IdeaProjects\\FirtWebStore\\src\\main\\webapp\\assets\\img\\" + fileName;
                            File file = new File(filePath);
                            item.write(file);
                            sysSetting.setValue("./assets/img/" + fileName);
                        } else {
                            out.println("Wrong File Formate");
                        }
                    } catch (Exception e) {
                        out.print(e);
                    }
                }

            }
            sysSettingDao.setSysSettings(sysSetting);
            response.sendRedirect("http://localhost:8080/FirtWebStore/site-settings");

        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

    private void menuSettings(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            String menuTitle = request.getParameter("menu-title");
            String menuSlug = request.getParameter("menu-slug");
            String menuOrder = request.getParameter("menu-order");
            MenuSetting menuSetting = new MenuSetting();
            menuSetting.setMenu_slug(menuSlug);
            menuSetting.setMenu_title(menuTitle);
            menuSetting.setMenu_order(Integer.parseInt(menuOrder));
            MenuSettingDao setMenuSetting = new MenuSettingDao();
            setMenuSetting.setMenuSettings(menuSetting);
//            sitesetting(request, response);
            response.sendRedirect("http://localhost:8080/FirtWebStore/site-settings");
        } catch (Exception e) {
            System.out.println("menu setting redirect to index :" + e.getMessage());
        }
    }

    private void pcategorysitesettings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        ProductCategory product;
        ProductCategoryDao ProductCategoryDao = new ProductCategoryDao();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        DiskFileItemFactory factory = new DiskFileItemFactory();
// Set factory constraints
        factory.setSizeThreshold(1024 * 1024);
        factory.setRepository(new File("d:\\temp"));
// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
// Set overall request size constraint
        upload.setSizeMax(1024 * 1024);
        try {
            product = new ProductCategory();
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    out.println(name + "............" + value + "........" + item.getContentType());
                    out.println(item);
                    switch (name) {
                        case "productcategoryname":
                            product.setName(value);
                            break;
                        case "productcategoryslug":
                            product.setSlug(value);
                            break;
                    }
                } else {
                    String fieldName = item.getFieldName(); //product_category_name
                    String fileName = item.getName(); //filename
                    String contentType = item.getContentType().toLowerCase();
//                    out.println(fileName+"........"+ contentType +"fieldName"+fieldName);
                    try {
                        if (contentType.contains("image")) {
                            fileName = product.name + "." + fileName.split("[.]")[1];
                            filePath = "C:\\Users\\manth\\IdeaProjects\\FirtWebStore\\src\\main\\webapp\\assets\\img\\" + fileName;
                            File file = new File(filePath);
                            item.write(file);
                            product.setImage("./assets/img/" + fileName);
                        } else {
                            out.println("Wrong File Formate");
                        }
                    } catch (Exception e) {
                        out.print(e);
                    }
                }

            }
            ProductCategoryDao.setProductCategory(product);
            response.sendRedirect("http://localhost:8080/FirtWebStore/site-settings");
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
    }

    private void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin/index.jsp").forward(request, response);
    }

    private void sitesetting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        SysSettingDao sysSettingDao = new SysSettingDao();
        MenuSettingDao menuSettingDao = new MenuSettingDao();
        SliderSettingDao sliderSettingDao = new SliderSettingDao();
        ProductCategoryDao productCategoryDao = new ProductCategoryDao();
        try {
            List<SysSetting> SysSettingList = sysSettingDao.getSysSetting();
            request.setAttribute("SysSettingList", SysSettingList);
            List<MenuSetting> MenuSettingList = menuSettingDao.getMenuSetting();
            request.setAttribute("MenuSettingList", MenuSettingList);
            List<SliderSetting> SliderSettingList = sliderSettingDao.getSliderSetting();
            request.setAttribute("SliderSettingList", SliderSettingList);
            List<ProductCategory> ProductCategoryList = productCategoryDao.getProductCategory();
            request.setAttribute("ProductCategoryList", ProductCategoryList);
        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("admin/sitesetting.jsp").forward(request, response);
    }

    private void contactInquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ContactUsDao contactUsDao = new ContactUsDao();
            ArrayList<ContactUs> getContactUsList = contactUsDao.getContactArrayList();
            request.setAttribute("ContactUsArrayList", getContactUsList);
            request.getRequestDispatcher("admin/contactInquiry.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void deleteProCat(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getParameter("id").matches("[0-9]+")) {
                ProductCategoryDao productCategoryDao = new ProductCategoryDao();
                productCategoryDao.deleteProductCat(Integer.parseInt(request.getParameter("id")));
                HttpSession session = request.getSession();
                session.setAttribute("message", "Product Cat Deleted");
                response.sendRedirect("http://localhost:8080/FirtWebStore/site-settings");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}