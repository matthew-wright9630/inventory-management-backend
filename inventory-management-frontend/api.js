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

function getAllLotNumbersByItemId(itemId) {
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
        .then((res) => {
            if (res.status === 204) return [];
            return res.json();
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

export {
    getAllWarehouses,
    getActiveStorageBinsInWarehouse,
    getAllItems,
    getAllItemsByItemDetailId,
    getAllLotNumbersByItemId,
    getQuantityOfItemId,
    getItemsByItemName,
    getItemsByStorageId,
};
