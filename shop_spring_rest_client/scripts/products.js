async function showAllProducts() {
    const res = await fetch('http://localhost:8080/shop_spring_rest_service/api/products');
    const productsJSON = await res.json();
    const products = JSON.parse(JSON.stringify(productsJSON));

    products.forEach(product => productToHTML(JSON.parse(JSON.stringify(product))));
}

window.addEventListener('DOMContentLoaded', showAllProducts);

function productToHTML(product) {
    const productList = document.getElementById("products");

    productList.insertAdjacentHTML('beforeend', `
        <div class="row mb-5">
            <h4 class="mb-3">Наименование: ${product.name} ; Цена: ${product.price}</h4>
            <div class="row mb-3">
                <div class="col-auto">
                    <input id="count${product.id}" type="text" required class="form-control" placeholder="Количество">
                </div>
                <div class="col-auto">
                    <button id="addItem${product.id}" class="btn btn-primary">Добавить позицию</button>
                </div>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Количество</th>
                        <th></th>
                        <th></th>
                        <th scope="col">Стоимость</th>
                        <th scope="col">Дата продажи</th>
                    </tr>
                </thead>
                <tbody id="productListTableBody${product.id}">
                </tbody>
            </table>
        </div>
    `);

    product.items.forEach(item => pasteItemsTable(item, product.id, product.price));

    document.getElementById('addItem' + product.id).addEventListener('click', async () => {
        const countElement = document.getElementById('count' + product.id);
        const count = Number.parseInt(countElement.value, 10);
        const cost = count * product.price;
        const res = await fetch('http://localhost:8080/shop_spring_rest_service/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "id": product.id,
                "name": product.name,
                "price": product.price,
                "items": [
                    {
                        "count": count,
                        "cost": cost
                    }
                ]
            })
        });
        const itemJSON = await res.json();
        console.log(itemJSON);
        location.reload();
        countElement.value = '';
    });
}

async function pasteItemsTable(item, productId, productPrice) {
    const saleRes = await fetch('http://localhost:8080/shop_spring_rest_service/api/sales/' + item.id);
    const saleJSON = await saleRes.json();
    const saleJSONString = JSON.stringify(saleJSON);

    let dateString = saleJSONString.substring(saleJSONString.indexOf("\"date\":") + 7);
    dateString = dateString.substring(0, dateString.indexOf(",")) + '';

    const date = new Date(Number.parseInt(dateString, 10));

    const productListTableBody = document.getElementById("productListTableBody" + productId);
    productListTableBody.insertAdjacentHTML('beforeend', `
        <tr>
            <th scope="row">${item.count}</th>
            <td><input id="itemCount${item.id}" required type="text" class="form-control" placeholder="${item.count}"></td>
            <td><button id="updateItem${item.id}" class="btn btn-primary">Редактировать</button></td>
            <td>${item.cost}</td>
            <td>${date.toLocaleString()}</td>
        </tr>
    `);

    document.getElementById("updateItem" + item.id).addEventListener('click', async () => {
        const itemCountElement = document.getElementById('itemCount' + item.id);
        const itemCount = Number.parseInt(itemCountElement.value, 10);
        const itemCost = itemCount * productPrice;
        const res = await fetch('http://localhost:8080/shop_spring_rest_service/api/items', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "id": item.id,
                "count": itemCount,
                "cost": itemCost
            })
        });
        const itemJSON = await res.json();
        console.log(itemJSON);
        location.reload();
        itemCountElement.value = '';
    });
}