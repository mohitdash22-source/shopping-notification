import { Link } from "react-router-dom";

function Cart({
                  cart,
                  increaseQuantity,
                  decreaseQuantity,
                  removeFromCart
              }) {

    const total = cart.reduce(
        (sum, item) =>
            sum + item.price * item.quantity,
        0
    );

    if (cart.length === 0) {

        return (
            <main className="page">

                <div className="empty-cart">

                    <div className="empty-icon">
                        🛒
                    </div>

                    <h1>
                        Your cart is empty
                    </h1>

                    <p>
                        Add some products to get started.
                    </p>

                    <Link
                        to="/products"
                        className="primary-link"
                    >
                        Continue Shopping
                    </Link>

                </div>

            </main>
        );
    }

    return (
        <main className="page">

            <h1>
                Your Cart
            </h1>

            <div className="cart-layout">

                <section className="cart-items">

                    {cart.map(item => (

                        <div
                            className="cart-item"
                            key={item.id}
                        >

                            <div className="cart-product-icon">
                                🛍️
                            </div>

                            <div className="cart-info">

                                <h3>
                                    {item.name}
                                </h3>

                                <p>
                                    ₹{item.price}
                                </p>

                            </div>

                            <div className="quantity-controls">

                                <button
                                    onClick={() =>
                                        decreaseQuantity(item.id)
                                    }
                                >
                                    −
                                </button>

                                <span>
                                    {item.quantity}
                                </span>

                                <button
                                    onClick={() =>
                                        increaseQuantity(item.id)
                                    }
                                >
                                    +
                                </button>

                            </div>

                            <strong>
                                ₹{item.price * item.quantity}
                            </strong>

                            <button
                                className="remove-button"
                                onClick={() =>
                                    removeFromCart(item.id)
                                }
                            >
                                Remove
                            </button>

                        </div>

                    ))}

                </section>

                <aside className="cart-summary">

                    <h2>
                        Order Summary
                    </h2>

                    <div className="summary-row">

                        <span>
                            Items
                        </span>

                        <span>
                            {cart.reduce(
                                (total, item) =>
                                    total + item.quantity,
                                0
                            )}
                        </span>

                    </div>

                    <div className="summary-row total">

                        <span>
                            Total
                        </span>

                        <strong>
                            ₹{total}
                        </strong>

                    </div>

                    <Link
                        to="/checkout"
                        className="checkout-button"
                    >
                        Proceed to Checkout
                    </Link>

                </aside>

            </div>

        </main>
    );
}

export default Cart;