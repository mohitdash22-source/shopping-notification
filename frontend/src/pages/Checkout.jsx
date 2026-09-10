import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { createOrder } from "../services/orderService";

function Checkout({ cart, clearCart }) {

    const navigate = useNavigate();

    const [address, setAddress] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const total = cart.reduce(
        (sum, item) =>
            sum + item.price * item.quantity,
        0
    );

    function handleCheckout(e) {

        e.preventDefault();

        if (cart.length === 0) {
            setError("Your cart is empty");
            return;
        }

        if (!address.trim()) {
            setError("Please enter your address");
            return;
        }

        setLoading(true);
        setError("");

        const order = {
            address: address,
            items: cart.map(item => ({
                productId: item.id,
                quantity: item.quantity
            }))
        };

        createOrder(order)
            .then(() => {

                clearCart();

                navigate("/orders");
            })
            .catch(error => {

                console.error(error);

                setError(
                    "Order could not be placed"
                );
            })
            .finally(() => {
                setLoading(false);
            });
    }

    if (cart.length === 0) {

        return (
            <main className="page">

                <h1>
                    Checkout
                </h1>

                <p>
                    Your cart is empty.
                </p>

                <Link to="/products">
                    Continue Shopping
                </Link>

            </main>
        );
    }

    return (
        <main className="page">

            <div className="checkout-card">

                <h1>
                    Checkout
                </h1>

                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}

                <form onSubmit={handleCheckout}>

                    <label>
                        Delivery Address
                    </label>

                    <textarea
                        value={address}
                        onChange={e =>
                            setAddress(e.target.value)
                        }
                        placeholder="Enter your delivery address"
                        rows="5"
                        required
                    />

                    <div className="checkout-total">

                        <span>
                            Total
                        </span>

                        <strong>
                            ₹{total}
                        </strong>

                    </div>

                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading
                            ? "Placing Order..."
                            : "Place Order"
                        }
                    </button>

                </form>

            </div>

        </main>
    );
}

export default Checkout;