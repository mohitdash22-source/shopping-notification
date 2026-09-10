import { useEffect, useState } from "react";
import { getOrders } from "../services/orderService";

function Orders() {

    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {

        getOrders()
            .then(data => {

                if (Array.isArray(data)) {
                    setOrders(data);
                } else {
                    setOrders(
                        data.content || []
                    );
                }

            })
            .catch(error => {

                console.error(error);

                setError(
                    "Failed to fetch orders"
                );

            })
            .finally(() => {
                setLoading(false);
            });

    }, []);

    return (
        <main className="page">

            <h1>
                My Orders
            </h1>

            {loading && (
                <p>
                    Loading orders...
                </p>
            )}

            {error && (
                <div className="error-message">
                    {error}
                </div>
            )}

            {!loading &&
                !error &&
                orders.length === 0 && (
                    <div className="empty-cart">

                        <h2>
                            No orders yet
                        </h2>

                        <p>
                            Your placed orders will appear here.
                        </p>

                    </div>
                )
            }

            <div className="orders-list">

                {orders.map((order, index) => (

                    <div
                        className="order-card"
                        key={order.id || index}
                    >

                        <div className="order-header">

                            <h3>
                                Order #{order.id || index + 1}
                            </h3>

                            <span>
                                {order.status || "PLACED"}
                            </span>

                        </div>

                        {order.address && (
                            <p>
                                <strong>
                                    Address:
                                </strong>{" "}
                                {order.address}
                            </p>
                        )}

                        {order.totalAmount && (
                            <p>
                                <strong>
                                    Total:
                                </strong>{" "}
                                ₹{order.totalAmount}
                            </p>
                        )}

                    </div>

                ))}

            </div>

        </main>
    );
}

export default Orders;