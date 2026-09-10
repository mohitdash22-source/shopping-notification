const API_URL = "http://localhost:8080";

function getHeaders() {

    const token = localStorage.getItem("token");

    return {
        "Content-Type": "application/json",
        ...(token && {
            Authorization: `Bearer ${token}`
        })
    };
}

export function getProducts() {

    return fetch(
        `${API_URL}/products?page=0&size=10`,
        {
            headers: getHeaders()
        }
    )
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Failed to fetch products"
                );
            }

            return response.json();
        });
}

export function searchProducts(name) {

    return fetch(
        `${API_URL}/products/search?name=${encodeURIComponent(name)}&page=0&size=10`,
        {
            headers: getHeaders()
        }
    )
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Search failed"
                );
            }

            return response.json();
        });
}

export function filterProducts(minPrice, maxPrice) {

    return fetch(
        `${API_URL}/products/filter?minPrice=${minPrice}&maxPrice=${maxPrice}&page=0&size=10`,
        {
            headers: getHeaders()
        }
    )
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Filter failed"
                );
            }

            return response.json();
        });
}

export function createProduct(product) {

    return fetch(
        `${API_URL}/products`,
        {
            method: "POST",
            headers: getHeaders(),
            body: JSON.stringify(product)
        }
    )
        .then(async response => {

            const data = await response.text();

            if (!response.ok) {
                throw new Error(
                    data || "Failed to create product"
                );
            }

            return data
                ? JSON.parse(data)
                : null;
        });
}