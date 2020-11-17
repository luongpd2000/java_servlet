<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 8/18/2020
  Time: 8:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<script src="resources/js/model/ajax_model_category.js"></script>
<script src="resources/js/pages/ajax_page_quan_ly_danh_muc.js"></script>

<main>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal10" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel10" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel10">Chi tiết danh mục sản phẩm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <label>Tên danh mục</label>
                                    <input type="text" class="form-control is-invalid" id="text-ten-danh-muc"  placeholder="Nhập tên danh mục...">
                                    <div class="invalid-feedback">
                                        Please choose a username.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-success" id="btn-luu-danh-muc">Lưu</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal11" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel11" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel11">Xác nhận thao tác</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                Bạn có chắc chắn xóa danh mục này không?
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-danger" id="btn-xac-nhan-xoa-danh-muc">Xóa</button>
                </div>
            </div>
        </div>
    </div>
    <div class="title-page mt-4">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h1>Quản Lý Danh Mục Sản Phẩm</h1>
                </div>
                <div class="col-12">
                    <hr>
                </div>
            </div>
        </div>
    </div>
    <div class="tool-page">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <button type="button" class="btn btn-primary " id="btn-them-danh-muc"><i class="fas fa-plus"></i> Thêm danh mục sản phẩm</button>
                </div>
                <div class="col-md-2 mt-1">
                    <select class="form-control" >
                        <option>Loại sản phẩm</option>
                        <option>Iphone</option>
                        <option>Bút</option>
                        <option>Dây điện</option>
                    </select>
                </div>
                <div class="col-md-2 mt-1">
                    <select class="form-control" >
                        <option>Sắp xếp</option>
                        <option>A->Z</option>
                        <option>Z->A</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="table-data">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="table-reponsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Tên Danh Mục</th>
                                <th scope="col">Hành Động</th>
                            </tr>
                            <tr>
                                <th scope="row"></th>
                                <td><input type="text" id="text-search-ten-danh-muc" class="form-control"></td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-primary" id="btn-tim-kiem-danh-muc"><i class="fas fa-search"></i> Tìm kiếm</button>
                                </td>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-warning mt-1" data-toggle="modal" data-target="#exampleModal10"><i class="fas fa-pencil-alt"></i> Sửa</button>
                                    <button type="button" class="btn btn-danger mt-1"  data-toggle="modal" data-target="#exampleModal11"><i class="fas fa-trash-alt"></i> Xóa</button>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Jacob</td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-warning mt-1" data-toggle="modal" data-target="#exampleModal10"><i class="fas fa-pencil-alt"></i> Sửa</button>
                                    <button type="button" class="btn btn-danger mt-1"  data-toggle="modal" data-target="#exampleModal11"><i class="fas fa-trash-alt"></i> Xóa</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>