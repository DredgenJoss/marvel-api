package com.marvel.marvelapi.marvel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.marvelapi.marvel.dto.GetMarvelCharacterDto;
import com.marvel.marvelapi.marvel.dto.GetMarvelComicsDto;
import com.marvel.marvelapi.utils.ConstantsUtils;
import com.marvel.marvelapi.utils.Signs;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MarvelService {

    private Signs signs = new Signs();
    private ObjectMapper objectMapper = new ObjectMapper();

    public Object getMarvelCharacters(GetMarvelCharacterDto getMarvelCharacterDto)
            throws JsonMappingException, JsonProcessingException {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/characters?ts=" + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelCharacterDto.params();

        List<Map<String, Object>> dataList = new ArrayList<>();
        JsonNode resolt = this.request(url).path("data").path("results");

        for (JsonNode item : resolt) {
            Map<String, Object> temporalItem = new HashMap<>();
            temporalItem.put("id", item.get("id").toString());
            temporalItem.put("name", item.get("name").toString().replace("\"", ""));
            temporalItem.put("comics", item.get("comics"));
            temporalItem.put("series", item.get("series"));

            dataList.add(temporalItem);

        }
        return dataList;

    }

    Object getMarvelComics(GetMarvelComicsDto getMarvelComicsDto) throws JsonMappingException, JsonProcessingException {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/comics?ts="
                + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelComicsDto.params();
        return this.request(url);
    }

    Object getMarvelComicById(GetMarvelComicsDto getMarvelComicsDto)
            throws JsonMappingException, JsonProcessingException {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/comics/" + getMarvelComicsDto.getComicId() + "?ts="
                + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelComicsDto.params();
        return this.request(url);
    }

    @SuppressWarnings("null")
    private JsonNode request(String url) throws JsonMappingException, JsonProcessingException {
        RequestEntity<Void> requestEntity = null;
        try {
            requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(url));
        } catch (URISyntaxException e) {
            System.out.println("ERROR: GET error, " + e.toString());
            return objectMapper.readTree("{}");
        }
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        return objectMapper.readTree(responseEntity.getBody());
    }

    public Object getComicsByCharacter(GetMarvelCharacterDto getMarvelCharacterDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getComicsByCharacter'");
    }

}
