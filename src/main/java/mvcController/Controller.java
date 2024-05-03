package mvcController;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.*;
import java.util.List;
import entities.Admin; 
import entities.Product;
import entities.Order;
import mvcModel.AdminService;
import mvcModel.ProductService;
import mvcModel.OrdersService;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private AdminService adminService;
    @EJB
    private ProductService productService;
    @EJB
    private OrdersService ordersService;

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        if (action != null && (action.equals("listProductsAdmin") || action.equals("addProductForm") || action.equals("listOrders") || action.equals("listAdmins"))) {
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (isAdmin == null || !isAdmin) {
                response.getWriter().write("Unauthorized Access");
                return;
            }
        }
        switch (action) {
            case "login":
                request.getRequestDispatcher("/login.html").forward(request, response);
                break;
            case "register":
                request.getRequestDispatcher("/register.html").forward(request, response);
                break;
            case "listAdmins":
                List<Admin> admins = adminService.listAdmins();
                request.setAttribute("admins", admins);
                request.getRequestDispatcher("/adminList.jsp").forward(request, response);
                break;
            case "addProductForm":
                request.getRequestDispatcher("/addProduct.html").forward(request, response);
                break;
            case "listProducts":
                List<Product> products = productService.getAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("/listproducts.jsp").forward(request, response);
                break;
            case "listOrders":
                List<Order> orders = ordersService.getAllOrders();
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/listorders.jsp").forward(request, response);
                break;
            case "deleteProduct":
                int productId = Integer.parseInt(request.getParameter("productId"));
                productService.deleteProduct(productId);
                response.sendRedirect("Controller?action=listProductsAdmin");
                break;
            case "editProduct":
                productId = Integer.parseInt(request.getParameter("productId"));
                Product product = productService.findProductById(productId);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/editproduct.jsp").forward(request, response);
                break;
            case "listProductsAdmin":
                List<Product> products1 = productService.getAllProducts();
                request.setAttribute("products", products1);
                request.getRequestDispatcher("/listproductsadmin.jsp").forward(request, response);
                break;
            default:
                response.getWriter().append("Action not supported. Served at: ").append(request.getContextPath());
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false); 

        if (action != null && (action.equals("addProduct") || action.equals("updateProduct"))) {
            boolean isAdmin = session != null && session.getAttribute("isAdmin") != null && (Boolean) session.getAttribute("isAdmin");
            if (!isAdmin) {
                response.getWriter().write("Unauthorized Access");
                return;
            }
        }

        if ("doLogin".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean isValid = adminService.validateLogin(username, password);
            if (isValid) {
                if (session == null) {
                    session = request.getSession(); 
                }
                session.setAttribute("username", username);
                session.setAttribute("isAdmin", true);
                response.sendRedirect("dashboard.html");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                request.getRequestDispatcher("/login.html").forward(request, response);
            }
        } else if ("doRegister".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            adminService.registerAdmin(username, password);
            response.sendRedirect("login.html");
        } else if ("addProduct".equals(action)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String imageurl = request.getParameter("imageurl");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setImageurl(imageurl);
            product.setPrice(price);
            productService.addProduct(product);
            response.sendRedirect("Controller?action=listProducts");
        } else if ("orderProduct".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String nameCli = request.getParameter("name_cli");
            String addressCli = request.getParameter("address_cli");
            String codepostal = request.getParameter("codepostal");
            String phoneCli = request.getParameter("phone_cli");

            Product product = productService.findProductById(productId);
            if (product != null) {
                Order order = new Order();
                order.setProduct(product);
                order.setNameCli(nameCli);
                order.setAddressCli(addressCli);
                order.setCodepostal(codepostal);
                order.setPhoneCli(phoneCli);
                ordersService.addOrder(order);
                response.sendRedirect("Controller?action=listProducts");
            } else {
                response.getWriter().append("Product not found");
            }
        } else if ("updateProduct".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String imageUrl = request.getParameter("imageurl");

            Product product = productService.findProductById(productId);
            if (product != null) {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setImageurl(imageUrl);
                productService.updateProduct(productId, product);
            }
            response.sendRedirect("Controller?action=listProductsAdmin");
        } else {
            doGet(request, response);
        }
    }

}
