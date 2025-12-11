import {
    getWarehouseDetails,
    getItemInformation,
    addActiveStorageBins,
} from "./script.js";

const parent = document.getElementById("storage-location-list");

function router() {
    const hash = window.location.hash;
    const hashParts = hash.split("/");

    let page = hashParts[1];
    let id = hashParts[2];

    if (page === "warehouses" && id) {
        showHideElements("warehouse-list");
        let children = document.getElementById("warehouse-list").children;
        document
            .getElementById("storage-location-list")
            .classList.remove("d-none");

        Array.from(children).forEach((child) => {
            if (child.id === "warehouse-" + id) {
                addActiveStorageBins(id);
                child.classList.add("col-12");
                child.classList.remove("d-none");
            } else {
                child.classList.add("d-none");
            }
        });
    } else if (page === "warehouses" && !id) {
        showHideElements("warehouse-list");
        let children = document.getElementById("warehouse-list").children;

        parent.innerHTML = "";

        Array.from(children).forEach((child) => {
            child.classList.remove("col-12");
            child.classList.remove("d-none");
        });
    } else if (page === "items" && id) {
        showHideElements("item-list");
        let children = document.getElementById("item-list").children;

        Array.from(children).forEach((child) => {
            if (child.id === "item-" + id) {
                child.classList.remove("d-none");
            } else {
                child.classList.add("d-none");
            }
        });
    } else if (page === "items") {
        showHideElements("item-list");
        let children = document.getElementById("item-list").children;

        parent.innerHTML = "";

        Array.from(children).forEach((child) => {
            child.classList.remove("d-none");
        });
    } else {
        showHideElements("no-items");
    }
}

function showHideElements(id) {
    let children = document.getElementById("section-container").children;
    Array.from(children).forEach((child) => {
        console.log(child);
        if (child.id === id) {
            document.getElementById(child.id).classList.add("row");
            document.getElementById(child.id).classList.remove("d-none");
        } else {
            document.getElementById(child.id).classList.add("d-none");
        }
    });
}

function dropStorageBins() {}

// document.getElementById("warehouse-btn").addEventListener("click", () => {
//     window.location.hash = "#/warehouses";
// });

// document.getElementById("item-btn").addEventListener("click", () => {
//     window.location.hash = "#/items";
// });

window.addEventListener("hashchange", router);
window.addEventListener("load", () => {
    getWarehouseDetails();
    getItemInformation();
    router();
});
