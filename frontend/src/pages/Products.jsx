import ProductCard from "../components/ProductCard";

function Products({
                      products,
                      loading,
                      error,
                      searchTerm,
                      maxPrice,
                      handleSearch,
                      handlePriceFilter,
                      addToCart,
                      isAdmin,
                      productName,
                      price,
                      setProductName,
                      setPrice,
                      handleSubmit
                  }) {

    return (
        <main className="page">

            <section className="hero">

                <div>
                    <p className="eyebrow">
                        SHOP SMART
                    </p>

                    <h1>
                        Find what you need.
                    </h1>

                    <p>
                        Explore our collection of products.
                    </p>
                </div>

            </section>

            <section className="toolbar">

                <input
                    type="text"
                    placeholder="Search products..."
                    value={searchTerm}
                    onChange={handleSearch}
                />

                <input
                    type="number"
                    placeholder="Maximum price"
                    value={maxPrice}
                    onChange={handlePriceFilter}
                />

            </section>

            {error && (
                <div className="error-message">
                    {error}
                </div>
            )}

            {loading ? (

                <div className="loading">
                    Loading products...
                </div>

            ) : (

                <section className="product-grid">

                    {products.length === 0 ? (

                        <p>
                            No products found.
                        </p>

                    ) : (

                        products.map(product => (

                            <ProductCard
                                key={product.id}
                                product={product}
                                onAddToCart={addToCart}
                            />

                        ))

                    )}

                </section>

            )}

            {isAdmin && (

                <section className="admin-panel">

                    <h2>
                        Admin — Add Product
                    </h2>

                    <form onSubmit={handleSubmit}>

                        <input
                            type="text"
                            placeholder="Product name"
                            value={productName}
                            onChange={e =>
                                setProductName(e.target.value)
                            }
                        />

                        <input
                            type="number"
                            placeholder="Price"
                            value={price}
                            onChange={e =>
                                setPrice(e.target.value)
                            }
                        />

                        <button type="submit">
                            Add Product
                        </button>

                    </form>

                </section>

            )}

        </main>
    );
}

export default Products;