package com.ex.FirstWebStore.front;

import com.ex.FirstWebStore.Setting.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FrontServlet", urlPatterns = {"/home", "/shop", "/contact", "/about", "/shop-single", "/product-cat/*"})
public class FrontServlet extends HttpServlet {
    List<SysSetting> settings = null;

    @Override
    public void init() throws ServletException {
        try {
            SysSettingDao settingDao = new SysSettingDao();
            MenuSettingDao menuSettingDao = new MenuSettingDao();
            getServletContext().setAttribute("logo", settingDao.getSysSetting("logo"));
            getServletContext().setAttribute("favicon", settingDao.getSysSetting("favicon"));
            getServletContext().setAttribute("menus", menuSettingDao.getMenuSetting());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/home";
        path = request.getServletPath();

        switch (path) {
            case "/home":
                loadIndex(request, response);
                break;
            case "/shop":
                loadShop(request, response);
                break;
            case "/contact":
                loadContact(request, response);
                break;
            case "/about":
                loadAbout(request, response);
                break;
            case "/product-cat":
                loadProduct(request, response);
                break;
            default:
                loadIndex(request, response);
                break;
        }

    }

    private void loadProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
//            String info = request.getPathInfo();
//            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(request.getQueryString()+"<br>");
            String slug = (request.getParameter("category"));
            String action = (request.getParameter("action"));
            ProductCategoryDao productCategoryDao = new ProductCategoryDao();
            ProductCategory productCategory = productCategoryDao.getProductCategory(slug);
            HttpSession session = request.getSession();
            session.setAttribute("productCat",productCategory);
            request.getRequestDispatcher("shop.jsp").forward(request,response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadAbout(HttpServletRequest request, HttpServletResponse response) {
        try {
            init();
            request.getRequestDispatcher("about.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadContact(HttpServletRequest request, HttpServletResponse response) {
        try {
            init();
            request.getRequestDispatcher("contact.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadShop(HttpServletRequest request, HttpServletResponse response) {
        try {
            init();
            ProductCategoryDao productCategoryDao = new ProductCategoryDao();
            List<ProductCategory> list = productCategoryDao.getProductCategory();
            request.setAttribute("shop",list);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadIndex(HttpServletRequest request, HttpServletResponse response) {
        try {
            init();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
