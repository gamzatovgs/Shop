async function showAllSales() {
    const res = await fetch('http://localhost:8080/shop_spring_rest_service/api/sales');
    const salesJSON = await res.json();
    const salesJSONString = JSON.stringify(salesJSON);
    const dateStrings = salesJSONString.substring(salesJSONString.indexOf("\"date\":") + 7).split("\"date\":");
    const sales = JSON.parse(salesJSONString);

    for (let i = 0; i < sales.length; i++) {
        const dateString = dateStrings[i].substring(0, dateStrings[i].indexOf(","));
        saleToHTML(JSON.parse(JSON.stringify(sales[i])), dateString);
    }
}

window.addEventListener('DOMContentLoaded', showAllSales);

function saleToHTML(sale, dateString) {
    const saleList = document.getElementById("sales");
    const date = new Date(Number.parseInt(dateString, 10));

    saleList.insertAdjacentHTML('beforeend', `
        <div class="row mb-5">
            <h4 id="date${sale.id}" class="mb-3">Дата: ${date.toLocaleString()}</h4>
            <div class="row mb-3">
                <div class="col-auto">
                    <input id="count${sale.id}" type="text" required class="form-control" placeholder="Количество">
                </div>
                <div class="col-auto">
                    <button id="addItem${sale.id}" class="btn btn-primary">Добавить позицию</button>
                </div>
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Наименование</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Количество</th>
                        <th scope="col">Стоимость</th>
                    </tr>
                </thead>
                <tbody id="saleListTableBody${sale.id}">
                </tbody>
            </table>
        </div>
    `);

    sale.items.forEach(item => pasteItemsTable(item, sale.id));

    document.getElementById('addItem' + sale.id).addEventListener('click', async () => {
        const countElement = document.getElementById('count' + sale.id);
        const priceElement = document.getElementById('price' + sale.id);
        const productIdElement = document.getElementById('productId' + sale.id);
        const productId = Number.parseInt(productIdElement.innerText, 10);
        console.log(productId);
        const count = Number.parseInt(countElement.value, 10);
        const cost = count * Number.parseFloat(priceElement.innerText);
        const res = await fetch('http://localhost:8080/shop_spring_rest_service/api/sales', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "id": sale.id,
                "items": [
                    {
                        "id": productId,
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

async function pasteItemsTable(item, saleId) {
    const productRes = await fetch('http://localhost:8080/shop_spring_rest_service/api/products/' + item.id);
    const productJSON = await productRes.json();
    const productJSONString = JSON.stringify(productJSON);
    const product = JSON.parse(productJSONString);

    const itemListTableBody = document.getElementById("saleListTableBody" + saleId);
    itemListTableBody.insertAdjacentHTML('beforeend', `
        <tr>
            <td>${product.name}</td>
            <td id="price${saleId}">${product.price}</td>
            <td>${item.count}</td>
            <td>${item.cost}</td>
            <td id="productId${saleId}" hidden>${product.id}</td>
        </tr>
    `);
}