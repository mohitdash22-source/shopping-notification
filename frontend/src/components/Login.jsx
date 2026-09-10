import { useState } from "react";
import { Link } from "react-router-dom";

function Login({ onLogin }) {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    function handleLogin(e) {

        e.preventDefault();

        setError("");
        setLoading(true);

        fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        })
            .then(async response => {

                const data = await response.text();

                console.log("Login status:", response.status);

                if (!response.ok) {
                    throw new Error(
                        data || "Invalid username or password"
                    );
                }

                localStorage.setItem("token", data);

                onLogin();
            })
            .catch(error => {

                console.error("Login error:", error);

                setError(
                    error.message === "Failed to fetch"
                        ? "Cannot connect to server"
                        : "Invalid username or password"
                );
            })
            .finally(() => {
                setLoading(false);
            });
    }

    return (
        <div className="auth-page">

            <div className="auth-card">

                <h1>ShopNotify</h1>

                <p className="auth-subtitle">
                    Welcome back
                </p>

                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}

                <form onSubmit={handleLogin}>

                    <input
                        type="text"
                        placeholder="Username"
                        value={username}
                        onChange={e =>
                            setUsername(e.target.value)
                        }
                        required
                    />

                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={e =>
                            setPassword(e.target.value)
                        }
                        required
                    />

                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading ? "Logging in..." : "Login"}
                    </button>

                </form>

                <p className="auth-link">
                    Don't have an account?
                    {" "}
                    <Link to="/register">
                        Sign Up
                    </Link>
                </p>

            </div>

        </div>
    );
}

export default Login;