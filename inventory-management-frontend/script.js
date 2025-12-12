import {
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
    createItemDetails,
    createStorageBin,
    createItem,
    createLotNumber,
    updateLocation,
    updateWarehouse,
} from "./api.js";

const listOfWarehouses = [];
const listOfItems = [];
const listOfActiveBins = [];

const itemWarehouseSelect = document.getElementById("item-warehouse-select");
const itemSubmitButton = document.getElementById("create-item-submit");
const inventoryOptions = document.getElementById("add-inventory-select");
const itemUpdateOptions = document.getElementById("inventory-select");

export function getWarehouseDetails() {
    getAllWarehouses().then((warehouseList) => {
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
    if (!newWarehouse.active) {
        return;
    }
    let warehouseDiv = document.createElement("div");
    let titleEl = document.createElement("h2");
    let addressEl = document.createElement("p");
    let capacityEl = document.createElement("p");
    let maxCapacityEl = document.createElement("p");
    let isActiveEl = document.createElement("p");

    warehouseDiv.classList.add(
        "col-4",
        "border",
        "rounded",
        "card",
        "bg-primary"
    );
    warehouseDiv.id = `warehouse-${newWarehouse.id}`;
    titleEl.innerText = newWarehouse.name;
    addressEl.innerText = `${newWarehouse.address}, ${
        newWarehouse.addressLineTwo
    },  ${newWarehouse.location?.stateOrRegion ?? ""}, ${
        newWarehouse.location?.country ?? ""
    }`;
    maxCapacityEl.innerText =
        "Maximum capacity: " + newWarehouse.maximumCapacity;
    capacityEl.innerText = "Current capacity: " + activeStorageBins.length;
    if (newWarehouse.active) {
        isActiveEl.innerText = "Warehouse is active";
    } else {
        isActiveEl.innerText = "Warehouse has been deactivated";
    }

    warehouseDiv.appendChild(titleEl);
    warehouseDiv.appendChild(addressEl);
    warehouseDiv.appendChild(capacityEl);
    warehouseDiv.appendChild(maxCapacityEl);
    warehouseDiv.appendChild(isActiveEl);

    document.getElementById("warehouse-list").appendChild(warehouseDiv);
    document
        .getElementById(`warehouse-${newWarehouse.id}`)
        .addEventListener("click", () => {
            window.location.hash = "#/warehouses/" + newWarehouse.id;
        });

    listOfWarehouses.push(newWarehouse);

    const option = document.createElement("option");
    option.value = newWarehouse.id;
    option.textContent = newWarehouse.name;
    itemWarehouseSelect.appendChild(option.cloneNode(true));
    inventoryOptions.appendChild(option.cloneNode(true));
}

//gets the list of item details, then finds the quantity in the network.
//also returns the quantity of the item at each warehouse
export function getItemInformation() {
    getAllItems()
        .then((itemDetailList) => {
            itemDetailList.map((itemDetail) => {
                listOfItems.push(itemDetail);
                getQuantityOfItemId(itemDetail.id).then((quantity) => {
                    addItemDetailsToList(itemDetail, quantity);
                });
            });
        })
        .catch((err) => console.error(err));
}

function addItemDetailsToList(itemDetail, itemQuantityObject) {
    let itemDiv = document.createElement("div");
    let titleEl = document.createElement("h2");
    let skuEl = document.createElement("p");
    let descriptionEl = document.createElement("p");
    let shelfLifeEl = document.createElement("p");
    let quantityEl = document.createElement("p");

    itemDiv.id = `item-${itemDetail.id}`;
    titleEl.innerText = itemDetail.name;
    skuEl.innerText = "SKU #: " + itemDetail.sku;
    descriptionEl.innerText = "Description: " + itemDetail.description;
    shelfLifeEl.innerText = "Shelf Life: " + itemDetail.shelfLife + " days";
    quantityEl.innerText =
        "Quantity of item in network: " + itemQuantityObject.quantity;

    itemDiv.appendChild(titleEl);
    itemDiv.appendChild(skuEl);
    itemDiv.appendChild(descriptionEl);
    itemDiv.appendChild(shelfLifeEl);
    itemDiv.appendChild(quantityEl);

    itemDiv.classList.add(
        "container-fluid",
        "col-4",
        "border",
        "rounded",
        "card",
        "bg-primary"
    );

    document.getElementById("item-list").appendChild(itemDiv);
    document
        .getElementById(`item-${itemDetail.id}`)
        .addEventListener("click", () => {
            window.location.hash = "#/items/" + itemDetail.id;
        });
}

export function addActiveStorageBins(warehouseId) {
    getActiveStorageBinsInWarehouse(warehouseId).then((activeStorageBins) => {
        activeStorageBins.map((storageBin) => {
            getItemsByStorageId(storageBin.id).then((itemList) => {
                addItemToList(storageBin, itemList);
            });
        });
    });
}

function addItemToList(storageBin, items) {
    let binDiv = document.createElement("div");
    binDiv.id = `storage-bin-${storageBin.id}`;
    binDiv.classList.add(
        "container-fluid",
        "col-6",
        "border",
        "rounded",
        "card",
        "bg-primary",
        "mb-3"
    );

    let binTitleEl = document.createElement("h2");
    binTitleEl.innerText = `Storage Bin: ${storageBin.storageLocation}`;
    binDiv.appendChild(binTitleEl);

    let itemListEl = document.createElement("ul");
    items.forEach((item) => {
        getLotNumbersByItemId(item.id).then((lot) => {
            if (lot.quantity === undefined) {
                lot.quantity = 0;
            }
            let itemLi = document.createElement("li");
            itemLi.innerHTML = `
            <strong>${item.itemDetail.name}</strong> (SKU: ${item.itemDetail.sku})<br>
            Quantity in Storage Bin: ${lot.quantity}
            `;
            itemListEl.appendChild(itemLi);
            itemListEl.classList.add("border");
        });
    });
    binDiv.appendChild(itemListEl);

    document.getElementById("storage-location-list").appendChild(binDiv);
}

document.getElementById("search-btn").addEventListener("click", () => {
    let text = document.getElementById("search-input").value;
    getItemsByItemName(text).then((itemDetail) => {
        if (itemDetail.length === 0) {
            window.location.hash = "#/no-item";
        } else {
            let children = document.getElementById("item-list").children;

            document.getElementById("item-list").classList.add("row");
            document.getElementById("item-list").classList.remove("d-none");
            document.getElementById("warehouse-list").classList.remove("row");
            document.getElementById("warehouse-list").classList.add("d-none");

            Array.from(children).forEach((child) => {
                if (child.id === "item-" + itemDetail.id) {
                    child.classList.remove("d-none");
                    window.location.hash = "#/items/" + itemDetail.id;
                } else {
                    child.classList.add("d-none");
                }
            });
        }
    });
    document.getElementById("search-input").value = "";
});

document
    .getElementById("warehouse-form")
    .addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        const name = formData.get("warehouse-name");
        const maxCapacity = formData.get("warehouse-max-capacity");
        const country = formData.get("warehouse-country");
        const stateOrRegion = formData.get("warehouse-state");
        const address = formData.get("warehouse-address");
        const addressLineTwo = formData.get("warehouse-address-line-two");

        createLocation(country, stateOrRegion)
            .then((location) => {
                createWarehouse(
                    name,
                    maxCapacity,
                    location.id,
                    address,
                    addressLineTwo
                ).then((warehouse) => {
                    if (warehouse) {
                        const container =
                            document.getElementById("warehouse-list");
                        container.innerHTML = "";
                        getWarehouseDetails();
                        document.getElementById("warehouse-form").reset();
                        document
                            .getElementById("form-list")
                            .classList.add("d-none");
                    }
                });
            })
            .catch((err) => console.error(err));
    });

document
    .getElementById("warehouse-create-btn")
    .addEventListener("click", () => {
        document.getElementById("form-list").classList.remove("d-none");
        document.getElementById("warehouse-form").classList.remove("d-none");
        document.getElementById("item-form").classList.add("d-none");
        document.getElementById("inventory-form").classList.add("d-none");
    });

document.getElementById("item-form").addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    const name = formData.get("item-name");
    const sku = formData.get("item-sku");
    const description = formData.get("item-description");
    const shelfLife =
        formData.get("item-shelf-life") === ""
            ? 9999
            : Number(formData.get("item-shelf-life"));
    const warehouseSelected = formData.get("item-warehouse-select" ?? "");
    const storageLocation = formData.get("item-storage-location" ?? "");
    const quantity =
        formData.get("item-quantity") === ""
            ? 0
            : Number(formData.get("item-quantity"));
    const manufacturedDate = new Date().toISOString().slice(0, 10);

    createItemDetails(name, sku, description, shelfLife)
        .then((itemDetail) => {
            if (storageLocation) {
                createStorageBin(warehouseSelected, storageLocation).then(
                    (bin) => {
                        createItem(bin.id, itemDetail.id).then((item) => {
                            createLotNumber(
                                quantity,
                                item.id,
                                manufacturedDate
                            ).then((lot) => {});
                        });
                    }
                );
            }
            if (itemDetail) {
                const container = document.getElementById("item-list");
                container.innerHTML = "";
                getItemInformation();
                document.getElementById("item-form").reset();
                document.getElementById("form-list").classList.add("d-none");
            }
        })
        .catch((err) => console.error(err));
});

document
    .getElementById("inventory-form")
    .addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        const select = document.getElementById("inventory-select");

        const itemName = select.options[select.selectedIndex].text;

        // const name = formData.get("inventory-select");
        const warehouseSelected = formData.get("add-inventory-select" ?? "");
        const storageLocation =
            formData.get("inventory-storage-location") === ""
                ? ""
                : formData.get("inventory-storage-location");
        const quantity =
            formData.get("inventory-quantity") === ""
                ? 0
                : Number(formData.get("inventory-quantity"));
        const manufacturedDate = new Date().toISOString().slice(0, 10);

        getItemsByItemName(itemName)
            .then((itemDetail) => {
                console.log(itemDetail);
                if (storageLocation) {
                    createStorageBin(warehouseSelected, storageLocation).then(
                        (bin) => {
                            console.log(bin, "BIN");
                            createItem(bin.id, itemDetail.id).then((item) => {
                                createLotNumber(
                                    quantity,
                                    item.id,
                                    manufacturedDate
                                ).then((lot) => {});
                            });
                        }
                    );
                }
                if (itemDetail) {
                    const container = document.getElementById("warehouse-list");
                    container.innerHTML = "";
                    getWarehouseDetails();
                    document.getElementById("inventory-form").reset();
                    document
                        .getElementById("form-list")
                        .classList.add("d-none");
                }
            })
            .catch((err) => console.error(err));
    });

document
    .getElementById("edit-warehouse-form")
    .addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);

        const hash = window.location.hash;
        const parts = hash.split("/");
        const warehouseId = Number(parts[2]);

        const name = formData.get("edit-warehouse-name");
        const maxCapacity = formData.get("edit-warehouse-max-capacity");
        const country = formData.get("edit-warehouse-country");
        const stateOrRegion = formData.get("edit-warehouse-state");
        const address = formData.get("edit-warehouse-address");
        const addressLineTwo = formData.get("edit-warehouse-address-line-two");

        updateLocation(country, stateOrRegion)
            .then((location) => {
                updateWarehouse(
                    warehouseId,
                    name,
                    maxCapacity,
                    location,
                    address,
                    addressLineTwo
                ).then((warehouse) => {
                    if (warehouse) {
                        console.log("Success?");
                        const container =
                            document.getElementById("warehouse-list");
                        container.innerHTML = "";
                        getWarehouseDetails();
                        document.getElementById("warehouse-form").reset();
                        document
                            .getElementById("form-list")
                            .classList.add("d-none");
                        window.location.hash = "#/warehouses/";
                    }
                });
            })
            .catch((err) => console.error(err));
    });

document.getElementById("item-create-btn").addEventListener("click", () => {
    document.getElementById("form-list").classList.remove("d-none");
    document.getElementById("warehouse-form").classList.add("d-none");
    document.getElementById("item-form").classList.remove("d-none");
    document.getElementById("inventory-form").classList.add("d-none");
});

document
    .getElementById("inventory-update-btn")
    .addEventListener("click", () => {
        getItemOptions();
        document.getElementById("form-list").classList.remove("d-none");
        document.getElementById("warehouse-form").classList.add("d-none");
        document.getElementById("item-form").classList.add("d-none");
        document.getElementById("inventory-form").classList.remove("d-none");
    });

document.getElementById("edit-warehouse-btn").addEventListener("click", () => {
    getItemOptions();
    document.getElementById("form-list").classList.remove("d-none");
    document.getElementById("warehouse-form").classList.add("d-none");
    document.getElementById("item-form").classList.add("d-none");
    document.getElementById("inventory-form").classList.add("d-none");
    document.getElementById("edit-warehouse-form").classList.remove("d-none");
});

document.getElementById("item-addition").addEventListener("change", (event) => {
    if (event.target.checked) {
        document.getElementById("warehouse-select").classList.remove("d-none");
        document.getElementById("storage-location").classList.remove("d-none");
        document
            .getElementById("quantity-of-addition")
            .classList.remove("d-none");
    } else {
        document.getElementById("warehouse-select").classList.add("d-none");
        document.getElementById("storage-location").classList.add("d-none");
        document.getElementById("quantity-of-addition").classList.add("d-none");
    }
});

itemWarehouseSelect.addEventListener("change", (event) => {
    listOfActiveBins = [];
    const value = Number(event.target.value);
    if (Number.isInteger(value)) {
        getActiveStorageBinsInWarehouse(value).then((bin) => {
            listOfActiveBins = bin;
        });
    }
});

document
    .getElementById("item-storage-location")
    .addEventListener("keyup", (event) => {
        const typedValue = event.target.value.trim();

        const exists = listOfActiveBins.some(
            (bin) => bin.storageLocation === typedValue
        );

        if (exists) {
            itemSubmitButton.disabled = true;
            itemSubmitButton.classList.add("disabled");
        } else {
            itemSubmitButton.disabled = false;
            itemSubmitButton.classList.remove("disabled");
        }
    });

function getItemOptions() {
    listOfItems.forEach((item) => {
        const option = document.createElement("option");
        option.value = item.id;
        option.textContent = item.name;
        itemUpdateOptions.appendChild(option);
    });
}

document.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
        document.getElementById("warehouse-select").classList.add("d-none");
    }
});

document.getElementById("close-btn").addEventListener("click", () => {
    document.getElementById("form-list").classList.add("d-none");
});
