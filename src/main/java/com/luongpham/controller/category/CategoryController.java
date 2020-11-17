package com.luongpham.controller.category;

import com.google.gson.Gson;
import com.luongpham.model.Category;
import com.luongpham.model.JsonResult;
import com.luongpham.service.CategoryService;
import com.luongpham.service_impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/api/v1/category/*")
public class CategoryController extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    private JsonResult jsonResult = new JsonResult();

    // thêm đối tượng
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = "";
        try {
            //Client sẽ chuyền mọt chuỗi json có định dangj tương ứng với chuỗi định dạng của một đối tượng category vào data body
            //Sau đó sử dụng gson để chuyển chuỗi json dang đối tượng category
            // dùng đối tượng này để get name truyền vào cho service insert

            //tham số đầu tiên của hàm fromJson có thể là String or một bộ đọc
            //tham số thứ 2 là class mà các bạn muốn chuyển từ json
            Category category = new Gson().fromJson(request.getReader(), Category.class);
            Category newCategory = categoryService.insert(category.getName());
            rs = newCategory != null ? jsonResult.jsonSuccess(category) : jsonResult.jsonSuccess("thêm danh mục thất bại");
        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("upload category fail !");
        }
        response.getWriter().write(rs);
    }

    //tìm kiếm đối tượng
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String partInfo = request.getPathInfo();
        String rs = "";
        if (partInfo.indexOf("/find-all") == 0) {
            try {
                List<Category> categoryList = categoryService.findAll();
//                rs = categoryList != null ? categoryList.toString() : null;
                rs = jsonResult.jsonSuccess(categoryList);
            } catch (Exception e) {
                e.printStackTrace();
//                rs = "find-all-error";
                rs = jsonResult.jsonFail("find-all-error");
            }
            response.getWriter().write(rs);
        } else if (partInfo.indexOf("/find-by-id") == 0) {
            try {
                //Lấy ra query ?id= để tìm ra category có id tương ứng
                int id = Integer.parseInt(request.getParameter("id"));
                //gọi đến service find by id nếu trả về kết quả được đưa về jsonSuccess
                // để chuyển kết quả từ đối tượng sang chuỗi chuẩn json
                Category category = categoryService.findById(id);
                rs = category != null ? jsonResult.jsonSuccess(category) : jsonResult.jsonSuccess("không tồn tại id tương ứng");
                //rs = jsonResult.jsonSuccess(categoryService.findById(id));
            } catch (Exception e) {
                //nếu trong quá trình thực hiện find by id có lỗi
                //hệ thốn sẽ in ra lỗi ở console server
                e.printStackTrace();
                //để trả về thông báo lỗi cho người dung theo chuẩn của chuẩn json
                rs = jsonResult.jsonFail("find=by-id-error");
            }
            response.getWriter().write(rs);
        } else {
            response.sendError(404, "URL is not supported");
        }
    }

    // sửa đối tượng
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = "";
        try {
            Category category = new Gson().fromJson(req.getReader(), Category.class);
            rs = jsonResult.jsonSuccess(categoryService.update(category));

        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("update category fail !");
        }
        resp.getWriter().write(rs);
    }

    //xóa đối tượng
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = "";
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            rs = jsonResult.jsonSuccess(categoryService.delete(id));
        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("delete category fail !");
        }
        resp.getWriter().write(rs);
    }
}
