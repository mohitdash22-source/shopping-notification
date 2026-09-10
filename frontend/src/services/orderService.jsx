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

export function createOrder(order) {

    return fetch(
        `${API_URL}/orders`,
        {
            method: "POST",
            headers: getHeaders(),
            body: JSON.stringify(order)
        }
    )
        .then(async response => {

            const data = await response.text();

            if (!response.ok) {
                throw new Error(
                    data || "Order failed"
                );
            }

            return data
                ? JSON.parse(data)
                : null;
        });
}

export function getOrders() {

    return fetch(
        `${API_URL}/orders`,
        {
            headers: getHeaders()
        }
    )
        .then(async response => {

            const data = await response.text();

            if (!response.ok) {
                throw new Error(
                    data || "Failed to fetch orders"
                );
            }

            return data
                ? JSON.parse(data)
                : [];
        });
}