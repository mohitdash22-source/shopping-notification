import { useParams, Link } from "react-router-dom";

function ProductDetails({ products, addToCart }) {

    const { id } = useParams();

    const product = products.find(
        item => String(item.id) === String(id)
    );

    if (!product) {

        return (
            <main className="page">

                <div className="not-found">

                    <h2>
                        Product not found
                    </h2>

                    <Link to="/products">
                        Back to Products
                    </Link>

                </div>

            </main>
        );
    }

    return (
        <main className="page">

            <Link
                to="/products"
                className="back-link"
            >
                ← Back to Products
            </Link>

            <section className="details-card">

                <div className="details-image">
                    🛍️
                </div>

                <div className="details-content">

                    <p className="eyebrow">
                        PRODUCT
                    </p>

                    <h1>
                        {product.name}
                    </h1>

                    <h2>
                        ₹{product.price}
                    </h2>

                    <p className="description">
                        This is a quality product available
                        in the ShopNotify store.
                    </p>

                    <button
                        onClick={() =>
                            addToCart(product)
                        }
                    >
                        Add to Cart
                    </button>

                </div>

            </section>

        </main>
    );
}

export default ProductDetails;