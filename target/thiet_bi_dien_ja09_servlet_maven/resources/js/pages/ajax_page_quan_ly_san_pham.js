// cú pháp đảm bảo các phần tử đã đc load xong và thực hiện thao tác lên nó

// b1 định nghĩa các thành phần phải thao tác
let selectSearchDanhMuc, selectSearchSapXep, textSearchTen, numberSearchGia, numberSearchDaBan, dateSearchNgayTao,
    selectSearchConHang, btnTimKiem, tableDuLieu, textTen, selectDanhMuc, numberGia, numberDaBan, numberBaoHanh,
    numberKhuyenMai, fileAnh, dateNgayTao, textareaGioiThieu, textareaThongSo, checkboxHetHang, btnLuuLai, btnXacNhanXoa ,btnThem;
let indexProduct , elementProduct;
let listProduct = [
    {
        id: 1,
        name: "Iphone 12",
        price: 10000,
        createDate: "2020-08-20",
        image: "https://cdn.cellphones.com.vn/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone11-purple-select-2019.png",
        introduction: "Iphone tai thỏ",
        specification: "Chip A11",
        soldOut: true,
        guarantee: 12,
        categoryId: 1,
        bouth: 1000,
        promotion: 10
    },
    {
        id: 2,
        name: "Iphone 11",
        price: 10000,
        createDate: "2020-08-20",
        image: "https://cdn.cellphones.com.vn/media/catalog/product/cache/1/image/1000x/040ec09b1e35df139433887a97daa66f/i/p/iphone11-green-select-2019.png",
        introduction: "Iphone tai thỏ",
        specification: "Chip A11",
        soldOut: false,
        guarantee: 12,
        categoryId: 2,
        bouth: 1000,
        promotion: 10
    },
];


$(async function () {
    selectSearchDanhMuc = $("#select-search-danh-muc");
    selectSearchSapXep = $("#select-search-sap-xep");
    textSearchTen = $("#text-search-ten");
    numberSearchGia = $("#number-search-gia");
    numberSearchDaBan = $("#number-search-da-ban");
    dateSearchNgayTao = $("#date-search-ngay-tao");
    selectSearchConHang = $("#select-search-con-hang");
    btnTimKiem = $("#btn-tim-kiem");
    tableDuLieu = $("tbody");
    textTen = $("#text-ten");
    selectDanhMuc = $("#select-danh-muc");
    numberGia = $("#number-gia");
    numberDaBan = $("#number-da-ban");
    numberBaoHanh = $("#number-bao-hanh");
    numberKhuyenMai = $("#number-khuyen-mai");
    fileAnh = $("#file-anh");
    dateNgayTao = $("#date-ngay-tao");
    textareaGioiThieu = $("#textarea-gioi-thieu");
    textareaThongSo = $("#textarea-thong-so");
    checkboxHetHang = $("#checkbox-het-hang");
    btnLuuLai = $("#btn-luu-lai");
    btnXacNhanXoa = $("#btn-xac-nhan-xoa");
    btnThem=$("#btn-them");

    await productFindAll().then(rs=> {
            if (rs.message === "success") {
                listProduct = rs.data;
            } else {
                listProduct = [];
            }
    }).catch(err => {
        console.log(err);
    })
    await viewDanhSachSanPham();
    await searchSanPham();
    await xacNhanXoaSanPham();
    await luuSanPham();
    await themSanPham();
})

// b2 tạo ra các hàm thao tác cần phải thao tác với 1 list sản phẩm đc trả về từ qpi

function viewDanhSachSanPham() {
    let view = "<tr><td colspan='8'><strong>Không có dữ liệu!</strong></td><tr/>";

    if (listProduct && listProduct.length > 0) {
        // map thực hiện duyệt lần lượt các phần tử trong mảng và nếu có return sẽ trả về 1 mảng mới là kết quả vùa thao tác đc
        view = listProduct.map((data, index) => {
            // template string : 1 chuỗi cho phép thưc hiện các phép toán trong cú pháp ${}
            return ` <tr data-index="${index}">
                                <th scope="row">${index + 1}</th>
                                <td><img src="${data.image}"
                                         alt="" width="80px"></td>
                                <td>${viewField(data.name)}</td>
                                <td>${viewField(data.price)}</td>
                                <td>${viewField(data.bouth)}</td>
                                <td>${viewField(data.createDate)}</td>
                                <td class="text-center">${data.soldOut ? `<span class="badge badge-danger">Hết hàng</span>` : `<span class="badge badge-success">Còn hàng</span>`}</td>
                                <td>
                                    <button type="button" class="btn btn-warning sua-san-pham"><i class="fas fa-pen"></i>
                                        Sửa</button>
                                    <button type="button" class="btn btn-danger xoa-san-pham" <i class="fas fa-trash-alt"></i>
                                        Xóa</button>
                                </td>
                            </tr>`
        }).join("");
    }

    tableDuLieu.html(view); // xóa hết html cũ view vào html mới truyền vào
    xoaSanPham();
    suaSanPham();
}


function searchSanPham() {
    // gán sự kiện click cho nút tìm kiếm và khi sự kiện xảy ra sẽ thực hiện các lệnh trong function
    btnTimKiem.click(function () {
        //B1: lấy ra các giá trị là các thông tin cần tìm kiếm

        let valSearchTen = textSearchTen.val();
        let valSearchGia = numberSearchGia.val();
        valSearchGia = valSearchGia.length > 0 ? valSearchGia : -1 ;
        let valSearchDaBan = numberSearchDaBan.val();
        valSearchDaBan = valSearchDaBan.length > 0 ? valSearchDaBan : -1 ;
        let valSearchNgayTao = dateSearchNgayTao.val();
        valSearchNgayTao = valSearchNgayTao.length > 0 ? valSearchNgayTao : null ;

        //B2 sau khi lấy ra đc các giá trị thì gọi api search và truyền vào các giá trị tìm kiếm
        // api search sẽ trả về 1 list sảm phẩm tìm kiếm tương ứng
        // sau đó lấy list trả về từ api gán vào listProduct và view lại list sp
        listProduct = [];
        viewDanhSachSanPham();
    })
}

function xoaSanPham() {     //  xác nhận xóa nếu để trong hàm này sẽ bị lưu nhiều id khi click
    $(".xoa-san-pham").click(function () {
        // b1 lấy index của phần tử trong mảng thông qua thuộc tính data-index trong tr
        // b2 lấy ra id của phần từ trong mảng
        // b3 gọi api xóa sản phẩm truywwnf vào id vừa tìm kiếm đc
        // b4 nếu api trả về true thì tực hiện xóa sp trong list và view lại

        // this thể hiện nút nào khi mình click
        indexProduct = $(this).parents("tr").attr("data-index");
        // phải đảm bải đc id tương ứng với nút xóa vừa click
        $("#exampleModal1").modal("show");
    })
}

function xacNhanXoaSanPham() {
    btnXacNhanXoa.click(function () {
        let idProduct = listProduct[indexProduct - 0].id;
        // call api và truyền vào id product nếu trả về true thì thực hiện product trong list
        listProduct = listProduct.filter((data , index) => {
            return index !== indexProduct;
        });
        viewDanhSachSanPham();
        $("#exampleModal1").modal("hide");
    })
}

function suaSanPham() {
    $(".sua-san-pham").click(function () {
        indexProduct = $(this).parents("tr").attr("data-index") - 0;
        elementProduct = listProduct[indexProduct];
        textTen.val(elementProduct.name);
        selectDanhMuc.val(elementProduct.categoryId);
        numberGia.val(elementProduct.price);
        numberDaBan.val(elementProduct.bouth);
        numberBaoHanh.val(elementProduct.guarantee);
        numberKhuyenMai.val(elementProduct.promotion);
        dateNgayTao.val(elementProduct.createDate);
        textareaGioiThieu.val(elementProduct.introduction);
        textareaThongSo.val(elementProduct.specification);
        checkboxHetHang.prop("checked" , elementProduct.soldOut);

        $("#exampleModal").modal("show");
    })
}

function checkData(selector , textError) {
    let val = $(selector).val();
    let check = false ;
    if(val.length > 0 ){
        check = true;
    }else {
        viewError(selector , textError);
    }
    // trả về 1 đối tượng có 2 thuộc tính là val và check
    return {val,check};
}


function luuSanPham() {
    btnLuuLai.click(function () {
        // kiểm tra dữ liệu người dùng nhập vào có đúng định dạng hay không
        let {val : valTen , check : checkTen} = checkData(textTen , "Định dạng tên không đúng");
        let valDanhMuc = selectDanhMuc.val();
        let {val:valGia, check:checkGia}= checkData(numberGia, "Giá bán phải là số");
        let {val:valDaBan, check:checkDaBan}= checkData(numberDaBan, "Nhập số lượng đã bán");
        let {val:valBaoHanh, check:checkBaoHanh}= checkData(numberBaoHanh, "Nhập thời gian bảo hành");
        let {val:valKhuyenMai, check:checkKhuyenMai}= checkData(numberKhuyenMai, "Nhập phần trăm khuyến mãi");
        let valGioiThieu = textareaGioiThieu.val();
        let valThongSo = textareaThongSo.val();
        let valHetHang = checkboxHetHang.is(":checked");

        if(checkTen && checkGia && checkBaoHanh && checkKhuyenMai && checkDaBan ){
            // nếu elementProduct là null thì tương ứng với thêm
            let checkAction = false ; // true là sửa , false là thêm
            if(elementProduct){
                checkAction = true;
            }else {
                elementProduct={};
            }
            elementProduct.name = valTen;
            elementProduct.categoryId = valDanhMuc;
            elementProduct.price = valGia;
            elementProduct.bouth = valDaBan;
            elementProduct.guarantee = valBaoHanh;
            elementProduct.promotion = valKhuyenMai;
            elementProduct.introduction = valGioiThieu;
            elementProduct.specification = valThongSo;
            elementProduct.soldOut = valHetHang;
            // call api sửa sp và truyền vào elementProduct hiện tạo
            // khi api trả về kq update thành công thì gán đối tượng vừa trả về vào list với index tương ứng

            if(checkAction) {
                listProduct[indexProduct] = elementProduct;
            }else {
                // cho nay em lay ra duoc mot elemetProduct la doi tuong ma nguoi dung nhap ththoogn tin vao
                //la mot doi tuong moi
                //ham ajax insert kia can truyen vao 1 doi tuong th``i em truyen thang elemte n``ay vao thoi
                listProduct.push(elementProduct);
            }
            // thêm và sửa chỉ khác nhau giữa thêm mới vào mảng và sửa lại
            viewDanhSachSanPham();
            $("#exampleModal").modal("hide");
        }
    })
}

function themSanPham() {
    // hàm có nvu xá đối tượng elementProduct và thực hiện mở modal
    btnThem.click(function () {
        elementProduct = null;
        $("#exampleModal").modal("show");
    })
}