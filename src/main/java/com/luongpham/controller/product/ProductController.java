package com.luongpham.controller.product;

import com.google.gson.Gson;
import com.luongpham.model.JsonResult;
import com.luongpham.model.Product;
import com.luongpham.service.ProductService;
import com.luongpham.service_impl.ProductService_Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/api/v1/product/*")
public class ProductController extends HttpServlet {

    private ProductService productService = new ProductService_Impl();
    private JsonResult jsonResult = new JsonResult();

    // thêm product
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rs = "";
        try {
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            Product newProduct = productService.insert(product);
            rs = newProduct != null ? jsonResult.jsonSuccess(product) : jsonResult.jsonSuccess("thêm sản phẩm thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("upload category fail !");
        }
        response.getWriter().write(rs);
    }

    // tìm kiếm
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //find all
        // find by id
        // find by category
        //search
        //sort by

        String partInfo = request.getPathInfo();
        String rs = "";
        if (partInfo.indexOf("/find-all") == 0) {
            try {
                List<Product> productList = productService.findAll();
                rs = jsonResult.jsonSuccess(productList);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("find-all-error");
            }
            response.getWriter().write(rs);

        } else if (partInfo.indexOf("/find-by-id") == 0) {
            try {
                //Lấy ra query ?id= để tìm ra category có id tương ứng
                int id = Integer.parseInt(request.getParameter("id"));
                //gọi đến service find by id nếu trả về kết quả được đưa về jsonSuccess
                // để chuyển kết quả từ đối tượng sang chuỗi chuẩn json
                Product product = productService.findById(id);
                rs = product != null ? jsonResult.jsonSuccess(product) : jsonResult.jsonSuccess("không tồn tại id tương ứng");
            } catch (Exception e) {
                //nếu trong quá trình thực hiện find by id có lỗi
                //hệ thốn sẽ in ra lỗi ở console server
                e.printStackTrace();
                //để trả về thông báo lỗi cho người dung theo chuẩn của chuẩn json
                rs = jsonResult.jsonFail("find=by-id-error");
            }
            response.getWriter().write(rs);

        } else if (partInfo.indexOf("/find-by-category") == 0) {
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            try {
                List<Product> productList = productService.findByCategory(idCategory);
                rs = jsonResult.jsonSuccess(productList);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("find-by-category error");
            }
            response.getWriter().write(rs);

        } else if (partInfo.indexOf("/search") == 0) {


        } else if (partInfo.indexOf("/sort-by-field") == 0) {
            String field = request.getParameter("field");
            boolean isAsc = Boolean.parseBoolean(request.getParameter("isAsc"));
            try {
                List<Product> productList = productService.sortBy(field, isAsc);
                rs = jsonResult.jsonSuccess(productList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                rs = jsonResult.jsonFail("sort-by" + field + " error");
            }
            response.getWriter().write(rs);

        } else if (partInfo.indexOf("sort-by-createDate") == 0) {
            try {
                List<Product> productList = productService.sortByCreateDate();
                rs = jsonResult.jsonSuccess(productList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                rs = jsonResult.jsonFail("sort-by-createDate error");
            }
            response.getWriter().write(rs);

        } else {
            response.sendError(404, "URL is not supported");
        }
    }

    // sửa
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = "";
        try {
            Product product = new Gson().fromJson(req.getReader(), Product.class);
            rs = jsonResult.jsonSuccess(productService.update(product));

        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("update category fail !");
        }
        resp.getWriter().write(rs);
    }

    // xóa
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = "";
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            rs = jsonResult.jsonSuccess(productService.delete(id));
        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("delete category fail !");
        }
        resp.getWriter().write(rs);
    }
}
