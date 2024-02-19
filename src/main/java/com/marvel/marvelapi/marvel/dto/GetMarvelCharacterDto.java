package com.marvel.marvelapi.marvel.dto;

public class GetMarvelCharacterDto {
    private int characterId;
    private String name;
    private int comics;
    private int series;
    private String orderBy;
    private int limit;
    private int offset;

    public GetMarvelCharacterDto(int characterId, String orderBy, int limit, int offset) {
        this.characterId = characterId;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }

    public GetMarvelCharacterDto(String name, int comics, int series, String orderBy, int limit, int offset) {
        this.name = name;
        this.comics = comics;
        this.series = series;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }

    public GetMarvelCharacterDto(String name) {
        this.name = name;
    }

    public String params() {
        String params = "";

        if (name != null && !name.equals("")) {
            params += "&name=" + name.replace(" ", "%");
        }

        if (comics > 0) {
            params += "&comics=" + comics;
        }

        if (series > 0) {
            params += "&series=" + series;
        }

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

    public int getCharacterId() {
        return characterId;
    }

    public String getName() {
        return name;
    }

    public int getComics() {
        return comics;
    }

    public int getSeries() {
        return series;
    }

    public int getLimit() {
        return offset;
    }

    public int getOffset() {
        return offset;
    }
}
