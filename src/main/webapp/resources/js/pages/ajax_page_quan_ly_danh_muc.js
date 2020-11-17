let btnThemDanhMuc, btnXacNhanXoaDanhMuc, btnTimKiemDanhMuc,
    textTenDanhMuc, btnLuuDanhMuc, textSearchTenDanhMuc,
    tableDuLieuDanhMuc, indexCategory, elementCategory;


let listDanhMuc = [
    {
        id: 1,
        name: "Điện thoại"
    },
    {
        id: 2,
        name: "Laptop"
    },
];

$(async function () {
    tableDuLieuDanhMuc = $("tbody");
    btnThemDanhMuc = $("#btn-them-danh-muc");
    btnXacNhanXoaDanhMuc = $("#btn-xac-nhan-xoa-danh-muc");
    btnLuuDanhMuc = $("#btn-luu-danh-muc");
    btnTimKiemDanhMuc = $("#btn-tim-kiem-danh-muc");
    textTenDanhMuc = $("#text-ten-danh-muc");
    textSearchTenDanhMuc = $("#text-search-ten-danh-muc");

    await categoryFindAll().then(rs => {
        if (rs.message === "success") {
            listDanhMuc = rs.data;
        } else {
            listDanhMuc = [];
        }
    }).catch(err => {
        console.log(err);
    });
    viewDanhMuc();
    searchDanhmuc();
    xacNhanXoaDanhMuc();
    themDanhMuc();
    luuDanhMuc();
})

function viewDanhMuc() {
    let view = "<tr><td colspan='8'><strong>Không có dữ liệu!</strong></td><tr/>";

    if (listDanhMuc && listDanhMuc.length > 0) {
        // map thực hiện duyệt lần lượt các phần tử trong mảng và nếu có return sẽ trả về 1 mảng mới là kết quả vùa thao tác đc
        view = listDanhMuc.map((data, index) => {
            // template string : 1 chuỗi cho phép thưc hiện các phép toán trong cú pháp ${}
            return ` <tr data-index="${index}">
                                <th scope="row">${index + 1}</th>
                                <td>${viewField(data.name)}</td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-warning mt-1 sua-danh-muc" ><i class="fas fa-pencil-alt"></i> Sửa</button>
                                    <button type="button" class="btn btn-danger mt-1 xoa-danh-muc"  ><i class="fas fa-trash-alt"></i> Xóa</button>
                                </td>
                            </tr>`
        }).join("");
    }

    tableDuLieuDanhMuc.html(view); // xóa hết html cũ view vào html mới truyền vào
    xoaDanhMuc();
    suaDanhMuc();
}

function searchDanhmuc() {
    btnTimKiemDanhMuc.click(function () {
        let valSearchTen = textSearchTenDanhMuc;
        listDanhMuc = [];
        viewDanhMuc();
    })
}

function xoaDanhMuc() {
    $(".xoa-danh-muc").click(function () {
        indexCategory = $(this).parents("tr").attr("data-index");
        // phải đảm bải đc id tương ứng với nút xóa vừa click
        $("#exampleModal11").modal("show");
    })
}

function xacNhanXoaDanhMuc() {
    btnXacNhanXoaDanhMuc.click(function () {
        let idCategory = listDanhMuc[indexCategory - 0].id;
        // call api và truyền vào id product nếu trả về true thì thực hiện product trong list
        listDanhMuc = listDanhMuc.filter((data, index) => {
            return index !== indexCategory;  // khác thì ko xóa , = thì xóa
        });
        viewDanhMuc();
        $("#exampleModal11").modal("hide");
    })
}

function suaDanhMuc() {
    $(".sua-danh-muc").click(function () {
        indexCategory = $(this).parents("tr").attr("data-index") - 0;
        elementCategory = listDanhMuc[indexCategory];
        textTenDanhMuc.val(elementCategory.name);

        $("#exampleModal10").modal("show");
    })
}

function checkData(selector, textError) {
    let val = $(selector).val();
    let check = false;
    if (val.length > 0) {
        check = true;
    } else {
        viewError(selector, textError);
    }
    // trả về 1 đối tượng có 2 thuộc tính là val và check
    return {val, check};
}

function luuDanhMuc() {
    btnLuuDanhMuc.click(function () {
        // kiểm tra dữ liệu người dùng nhập vào có đúng định dạng hay không
        let {val: valTen, check: checkTen} = checkData(textTenDanhMuc, "Định dạng tên không đúng");

        if (checkTen) {
            // nếu elementProduct là null thì tương ứng với thêm
            let checkAction = false; // true là sửa , false là thêm
            if (elementCategory) {
                checkAction = true;
            } else {
                elementCategory = {};
            }
            elementCategory.name = valTen;
            // call api sửa sp và truyền vào elementProduct hiện tạo
            // khi api trả về kq update thành công thì gán đối tượng vừa trả về vào list với index tương ứng

            if (checkAction) {
                listDanhMuc[indexCategory] = elementCategory;
            } else {
                // cho nay em lay ra duoc mot elemetProduct la doi tuong ma nguoi dung nhap ththoogn tin vao
                //la mot doi tuong moi
                //ham ajax insert kia can truyen vao 1 doi tuong th``i em truyen thang elemte n``ay vao thoi
                listDanhMuc.push(elementCategory);
            }
            // thêm và sửa chỉ khác nhau giữa thêm mới vào mảng và sửa lại
            viewDanhMuc();
            $("#exampleModal10").modal("hide");
        }
    })
}

function themDanhMuc() {
    btnThemDanhMuc.click(function () {
        elementCategory = null;
        $("#exampleModal10").modal("show");
    })
}