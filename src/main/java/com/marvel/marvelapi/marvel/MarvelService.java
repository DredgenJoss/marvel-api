package com.marvel.marvelapi.marvel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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

    public Object getMarvelCharacters(GetMarvelCharacterDto getMarvelCharacterDto) {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/characters?ts=" + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelCharacterDto.params();

        return this.request(url);
    }

    Object getComicsByCharacter(GetMarvelCharacterDto getMarvelCharacterDto) {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/characters/" + getMarvelCharacterDto.getCharacterId() + "/comics?ts="
                + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelCharacterDto.params();
        return this.request(url);
    }

    Object getMarvelComics(GetMarvelComicsDto getMarvelComicsDto) {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/comics?ts="
                + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelComicsDto.params();
        return this.request(url);
    }

    Object getMarvelComicById(GetMarvelComicsDto getMarvelComicsDto) {

        Map<String, String> sign = signs.generateHash();

        String url = ConstantsUtils.BASE_URL + "/comics/" + getMarvelComicsDto.getComicId() + "?ts="
                + sign.get("timestamp") + "&apikey="
                + ConstantsUtils.PUBLIC_KEY + "&hash="
                + sign.get("hash") + getMarvelComicsDto.params();
        return this.request(url);
    }

    @SuppressWarnings("null")
    private Object request(String url) {
        RequestEntity<Void> requestEntity = null;
        try {
            requestEntity = new RequestEntity<>(HttpMethod.GET, new URI(url));
        } catch (URISyntaxException e) {
            System.out.println("ERROR: GET error, " + e.toString());
            return "{}";
        }
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(requestEntity, String.class);
        Object response = new HashMap<>();
        response = responseEntity.getBody();
        return response;
    }

}
