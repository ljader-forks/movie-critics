package com.rys.moviecriticts.rate.rest.dto;

public class PageableDto {

    private final int size;
    private final int page;

    public PageableDto(final int size, final int page) {
        this.size = size;
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }
}
