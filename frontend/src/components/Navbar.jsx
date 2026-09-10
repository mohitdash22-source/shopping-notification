import { Link } from "react-router-dom";

function Navbar({ cartCount, onLogout }) {

    return (
        <nav className="navbar">

            <Link
                to="/products"
                className="brand"
            >
                🛒 ShopNotify
            </Link>

            <div className="nav-links">

                <Link to="/products">
                    Products
                </Link>

                <Link to="/orders">
                    Orders
                </Link>

                <Link to="/cart">
                    🛒 Cart ({cartCount})
                </Link>

                <button onClick={onLogout}>
                    Logout
                </button>

            </div>

        </nav>
    );
}

export default Navbar;