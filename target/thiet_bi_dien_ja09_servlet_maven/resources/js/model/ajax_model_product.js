const urlProduct = "product/";

function productFindAll() {
    return ajaxGet(`${urlProduct}find-all`)
}

function productFindById(id) {
    return ajaxGet(`${urlProduct}find-by-id`)
}

function productInsert(product) {
    return ajaxPost(`${urlProduct}`)
}

function productUpdate(product) {
    return ajaxPut(`${urlProduct}`)
}

function productDelete(id) {
    return ajaxDelete(`${urlProduct}`)
}

function productSortByCreateDate() {
    return ajaxGet(`${urlProduct}sort-by-createDate`)
}

function productFindByCategory() {
    return ajaxGet(`${urlProduct}find-by-category`)
}

function productSortBy() {
    return ajaxGet(`${urlProduct}sort-by-field`)
}

function productSearch() {
    return ajaxGet(`${urlProduct}search`)
}
