package com.mohit.shoppingnotification.dto;

import java.util.List;

public class PaginationResponseDTO {

    private List<ProductResponseDTO> products;

    private int currentPage;
    private int pageSize;
    private long totalProducts;
    private int totalPages;
    private boolean firstPage;
    private boolean lastPage;

    // getters and setters
    public void setProducts(List<ProductResponseDTO> products) {
        this.products = products;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<ProductResponseDTO> getProducts() {
        return products;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }
}