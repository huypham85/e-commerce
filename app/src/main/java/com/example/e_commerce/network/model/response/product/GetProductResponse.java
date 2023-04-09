package com.example.e_commerce.network.model.response.product;


import java.util.List;

public class GetProductResponse {
    private List<ProductResponse> content;
    private boolean last;
    private long totalPages;
    private long totalElements;
    private boolean first;

    public GetProductResponse(List<ProductResponse> productResponse, boolean last, long totalPages, long totalElements, boolean first) {
        this.content = productResponse;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
    }

    public List<ProductResponse> getContent() {
        return content;
    }

    public void setContent(List<ProductResponse> value) {
        this.content = value;
    }

    public boolean getLast() {
        return last;
    }

    public void setLast(boolean value) {
        this.last = value;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long value) {
        this.totalPages = value;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long value) {
        this.totalElements = value;
    }

    public boolean getFirst() {
        return first;
    }

    public void setFirst(boolean value) {
        this.first = value;
    }


}
