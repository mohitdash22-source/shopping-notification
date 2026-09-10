import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function Register() {

    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    function handleRegister(e) {

        e.preventDefault();

        setError("");
        setMessage("");

        fetch("http://localhost:8080/auth/register", {
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

                if (!response.ok) {
                    throw new Error(
                        data || "Registration failed"
                    );
                }

                setMessage(
                    "Registration successful! Redirecting to login..."
                );

                setTimeout(() => {
                    navigate("/");
                }, 1200);
            })
            .catch(error => {

                console.error(error);

                setError(
                    error.message === "Failed to fetch"
                        ? "Cannot connect to server"
                        : error.message
                );
            });
    }

    return (
        <div className="auth-page">

            <div className="auth-card">

                <h1>ShopNotify</h1>

                <p className="auth-subtitle">
                    Create your account
                </p>

                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}

                {message && (
                    <div className="success-message">
                        {message}
                    </div>
                )}

                <form onSubmit={handleRegister}>

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

                    <button type="submit">
                        Create Account
                    </button>

                </form>

                <p className="auth-link">
                    Already have an account?
                    {" "}
                    <Link to="/">
                        Login
                    </Link>
                </p>

            </div>

        </div>
    );
}

export default Register;