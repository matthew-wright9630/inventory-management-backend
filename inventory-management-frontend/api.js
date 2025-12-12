const URL = "http://localhost:8080";

function getAllWarehouses() {
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

function getAllItems() {
    return fetch(URL + "/item-details", {
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

function getAllItemsByItemDetailId(itemDetailId) {
    return fetch(`${URL}/items/item-details/${itemDetailId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            if (res.status === 204) return [];
            return res.json();
        })
        .catch((err) => {
            console.error(err);
        });
}

function getItemsByItemName(itemName) {
    return fetch(`${URL}/item-details/item/?itemName=${itemName}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.text())
        .then((text) => {
            if (!text) return [];
            return JSON.parse(text);
        })
        .catch((err) => {
            console.error(err);
        });
}

function getItemsByStorageId(storageId) {
    return fetch(`${URL}/items/storage-bin/${storageId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.text())
        .then((text) => {
            if (!text) return [];
            return JSON.parse(text);
        })
        .catch((err) => {
            console.error(err);
        });
}

function getLotNumbersByItemId(itemId) {
    return fetch(URL + "/lot-numbers/item/" + itemId, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.text())
        .then((text) => {
            if (!text) return [];
            return JSON.parse(text);
        })
        .catch((err) => {
            console.error(err);
        });
}

function getQuantityOfItemId(itemId) {
    return fetch(`${URL}/item-quantity/${itemId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => res.text())
        .then((text) => {
            if (!text) return [];
            return JSON.parse(text);
        })
        .catch((err) => {
            console.error(err);
        });
}

function getItemDetailById(itemId) {
    return fetch(`${URL}/item-details/${itemId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((res) => {
            if (res.status === 204) return [];
            return res.json();
        })
        .catch((err) => {
            console.error(err);
        });
}

function createLocation(country, stateOrRegion) {
    const locationData = {
        country: country,
        stateOrRegion: stateOrRegion,
    };
    return fetch(`${URL}/locations`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(locationData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function createWarehouse(
    warehouseName,
    maxCapacity,
    locationId,
    address,
    addressLineTwo
) {
    const warehouseData = {
        name: warehouseName,
        maximumCapacity: maxCapacity,
        address: address,
        addressLineTwo: addressLineTwo,
    };
    return fetch(`${URL}/warehouses?locationId=${locationId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(warehouseData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function createStorageBin(warehouseId, storageLocation) {
    const storageBinData = {
        storageLocation: storageLocation,
    };
    return fetch(`${URL}/storage-bin?warehouseId=${warehouseId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(storageBinData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function createItemDetails(name, sku, description, shelfLife) {
    if (shelfLife === "") {
        shelfLife = 0;
    }
    const itemDetailData = {
        name: name,
        sku: sku,
        description: description,
        shelfLife: shelfLife,
    };
    return fetch(URL + "/item-details", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(itemDetailData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function createLotNumber(quantity, itemId, manufacturedDate) {
    const lotNumberData = {
        quantity: quantity,
        manufactureDate: manufacturedDate,
    };
    return fetch(`${URL}/lot-numbers?itemId=${itemId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(lotNumberData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function createItem(storageBinId, itemDetailId) {
    const itemData = {};
    return fetch(
        `${URL}/items?storageBinId=${storageBinId}&itemDetailId=${itemDetailId}`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(itemData),
        }
    )
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function updateLocation(country, stateOrRegion) {
    const locationData = {
        country: country,
        stateOrRegion: stateOrRegion,
    };
    return fetch(`${URL}/locations`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(locationData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function updateWarehouse(
    warehouseId,
    warehouseName,
    maxCapacity,
    location,
    address,
    addressLineTwo
) {
    const warehouseData = {
        id: warehouseId,
        name: warehouseName,
        maximumCapacity: maxCapacity,
        address: address,
        addressLineTwo: addressLineTwo,
        location: location,
    };
    return fetch(`${URL}/warehouses?locationId=${location.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(warehouseData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function updateLotNumber(quantity, lotId, manufacturedDate) {
    const lotNumberData = {
        quantity: quantity,
        manufactureDate: manufacturedDate,
    };
    return fetch(`${URL}/lot-numbers/${lotId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(lotNumberData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

function deleteWarehouse(warehouseId) {
    return fetch(`${URL}/warehouses/${warehouseId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
    }).catch((err) => console.error(err));
}

function deleteItemDetails(itemDetailId) {
    return fetch(`${URL}/item-details/${itemDetailId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
    }).catch((err) => console.error(err));
}

function updateItemDetail(id, name, sku, description, shelfLife) {
    const itemDetailsData = {
        id: id,
        name: name,
        sku: sku,
        description: description,
        shelfLife: shelfLife,
    };
    return fetch(`${URL}/item-details`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(itemDetailsData),
    })
        .then((res) => {
            return res.json();
        })
        .catch((err) => console.error(err));
}

export {
    getAllWarehouses,
    getActiveStorageBinsInWarehouse,
    getAllItems,
    getAllItemsByItemDetailId,
    getLotNumbersByItemId,
    getQuantityOfItemId,
    getItemsByItemName,
    getItemsByStorageId,
    createLocation,
    createWarehouse,
    createStorageBin,
    createItemDetails,
    createItem,
    createLotNumber,
    updateLocation,
    updateWarehouse,
    updateLotNumber,
    deleteWarehouse,
    deleteItemDetails,
    updateItemDetail,
};
