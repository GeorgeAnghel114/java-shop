package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = {"/supplier/"},loadOnStartup=2)
public class FilterBySupplier extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Map<String, Object> params = new HashMap<>();

        if(!req.getParameter("supplier").equals("")) {
            int supplierId = Integer.parseInt(req.getParameter("supplier"));
            Supplier supplier = productService.getSupplierDao().find(supplierId);

            params.put("categories", productService.getProductCategoryDao().getAll());
            params.put("products", productService.getProductDao().getBy(supplier));
            params.put("suppliers", productService.getSupplierDao().getAll());
        }
        context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }
}
