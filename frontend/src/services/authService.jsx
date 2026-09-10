const API_URL = "http://localhost:8080";

export function login(username, password) {

    return fetch(
        `${API_URL}/auth/login`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        }
    )
        .then(async response => {

            const data = await response.text();

            if (!response.ok) {
                throw new Error(
                    data || "Login failed"
                );
            }

            return data;
        });
}

export function register(username, password) {

    return fetch(
        `${API_URL}/auth/register`,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        }
    )
        .then(async response => {

            const data = await response.text();

            if (!response.ok) {
                throw new Error(
                    data || "Registration failed"
                );
            }

            return data;
        });
}