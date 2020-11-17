const urlCategory = "category/";

function categoryFindAll() {
    return ajaxGet(`${urlCategory}find-all`)
}

function categoryFindById(id) {
    return ajaxGet(`${urlCategory}find-by-id`)
}

function categoryInsert(nameCategory) {
    return ajaxPost(`${urlCategory}`)
}

function categoryUpdate(category) {
    return ajaxPut(`${urlCategory}`)
}

function categoryDelete(id) {
    return ajaxDelete(`${urlCategory}`)
}

