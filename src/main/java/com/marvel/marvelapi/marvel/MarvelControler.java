package com.marvel.marvelapi.marvel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.marvelapi.marvel.dto.GetMarvelCharacterDto;
import com.marvel.marvelapi.marvel.dto.GetMarvelComicsDto;

@RestController
@RequestMapping("/api/marvel")
public class MarvelControler {

    private final MarvelService MarvelService = new MarvelService();

    // 1. Buscar a los personajes de Marvel por nombre, historietas y serie.
    @GetMapping("/characters")
    public ResponseEntity<Object> getMarvelCharacters(
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "comics", required = false, defaultValue = "-1") int comics,
            @RequestParam(name = "series", required = false, defaultValue = "-1") int series,
            @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {

        GetMarvelCharacterDto getMarvelCharacterDto = new GetMarvelCharacterDto(name, comics, series, orderBy, limit,
                offset);

        Object body = this.MarvelService.getMarvelCharacters(getMarvelCharacterDto);
        return this.response(body);

    }

    // 2. Obtener el listado de comic que tiene un personaje especifico.
    @GetMapping("/characters/{characterId}/comics")
    public ResponseEntity<Object> getComicsByCharacter(
            @PathVariable int characterId,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {

        GetMarvelCharacterDto getMarvelCharacterDto = new GetMarvelCharacterDto(characterId, orderBy, limit,
                offset);

        Object body = this.MarvelService.getComicsByCharacter(getMarvelCharacterDto);
        return this.response(body);

    }

    // 3. Obtener la imagen y descripción de un personaje especifico.
    @GetMapping("/characters/info")
    public ResponseEntity<Object> getMarvelCharacterData(
            @RequestParam(name = "name", required = false, defaultValue = "") String name) {

        GetMarvelCharacterDto getMarvelCharacterDto = new GetMarvelCharacterDto(name);
        Object body = this.MarvelService.getMarvelCharacters(getMarvelCharacterDto);
        return this.response(body);

    }

    // 4. Mostrar listas de comic completas
    @GetMapping("/comics")
    public ResponseEntity<Object> getMarvelComics(
            @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {

        GetMarvelComicsDto getMarvelComicsDto = new GetMarvelComicsDto(orderBy, limit, offset);
        Object body = this.MarvelService.getMarvelComics(getMarvelComicsDto);
        return this.response(body);

    }

    // 5. Mostrar comic filtrado por id
    @GetMapping("/comics/{comicId}")
    public ResponseEntity<Object> getMarvelComicById(
            @PathVariable int comicId,
            @RequestParam(name = "orderBy", required = false, defaultValue = "") String orderBy,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset) {

        GetMarvelComicsDto getMarvelComicsDto = new GetMarvelComicsDto(comicId, orderBy, limit, offset);
        Object body = this.MarvelService.getMarvelComicById(getMarvelComicsDto);
        return this.response(body);

    }
    // 6. Mostrar las búsquedas que cualquier usuario haya hecho relacionadas a
    // historietas.
    // ? get
    // 7. Mostrar las búsquedas que un usuario especifico ha realizado

    @SuppressWarnings("null")
    private ResponseEntity<Object> response(Object body) {
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(body);
    }

    // TODO: .ENV
    // TODO: FORMATEAR RESPUESTA

    // TODO: SPRING SECURITY

}
