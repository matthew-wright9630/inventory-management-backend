const URL = "http://localhost:8080";
let warehouses = [];
let storageBins = [];

function getWarehouse() {
    getAllWarehousesAndStorageBins().then((warehouseList) => {
        warehouseList.map((warehouse) => {
            getActiveStorageBinsInWarehouse(warehouse.id).then(
                (activeStorageBins) => {
                    addWarehouseToList(warehouse, activeStorageBins);
                }
            );
        });
    });
}

function addWarehouseToList(newWarehouse, activeStorageBins) {
    console.log(newWarehouse, activeStorageBins);
    let warehouseDiv = document.createElement("div");
    let titleEl = document.createElement("h2");
    let addressEl = document.createElement("p");
    let capacityEl = document.createElement("p");
    let maxCapacityEl = document.createElement("p");

    titleEl.innerText = newWarehouse.name;
    addressEl.innerText = `${newWarehouse.address} ${newWarehouse.addressLineTwo}, ${newWarehouse.location.stateOrRegion}, ${newWarehouse.location.country}`;
    maxCapacityEl.innerText = newWarehouse.maximumCapacity;
    capacityEl = activeStorageBins.length();

    warehouseDiv.appendChild(titleEl);

    document.getElementById("warehouse-list").appendChild(warehouseDiv);
}

function getAllWarehousesAndStorageBins() {
    return fetch(URL + "/warehouses", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => {
            console.error(err);
        });
}

function getActiveStorageBinsInWarehouse(warehouseId) {
    return fetch(`${URL}/storage-bin/warehouse/${warehouseId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => {
            console.error(err);
        });
}

document
    .getElementById("warehouse-test")
    .addEventListener("click", getWarehouse);
