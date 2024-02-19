package com.marvel.marvelapi.marvel.dto;

public class GetMarvelComicsDto {
    private int comicId;
    private String orderBy;
    private int limit;
    private int offset;

    public GetMarvelComicsDto(String orderBy, int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }

    public GetMarvelComicsDto(int comicId, String orderBy, int limit, int offset) {
        this.comicId = comicId;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }

    public String params() {
        String params = "";

        if (limit > 0) {
            params += "&limit=" + limit;
        }

        if (offset > 0) {
            params += "&offset=" + offset;
        }

        if (orderBy != null && !orderBy.equals("")) {
            orderBy += "&name=" + orderBy;
        }

        return params;

    }

    public int getComicId() {
        return comicId;
    }

    public int getLimit() {
        return offset;
    }

    public int getOffset() {
        return offset;
    }
}
