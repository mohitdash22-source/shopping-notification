import { Link } from "react-router-dom";

function ProductCard({ product, onAddToCart }) {

    return (
        <div className="product-card">

            <div className="product-icon">
                🛍️
            </div>

            <h3>
                {product.name}
            </h3>

            <p className="product-price">
                ₹{product.price}
            </p>

            <div className="product-actions">

                <Link
                    to={`/products/${product.id}`}
                    className="details-button"
                >
                    View Details
                </Link>

                <button
                    onClick={() =>
                        onAddToCart(product)
                    }
                >
                    Add to Cart
                </button>

            </div>

        </div>
    );
}

export default ProductCard;