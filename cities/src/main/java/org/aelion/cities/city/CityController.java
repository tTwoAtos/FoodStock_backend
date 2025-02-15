package org.aelion.cities.city;

import org.aelion.cities.city.CityService.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<City> cities = service.getAll();
            return ResponseEntity.ok(cities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des villes : " + e.getMessage());
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code) {
        try {
            City city = service.getByCode(code);
            return ResponseEntity.ok(city);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ville non trouvée avec le code : " + code);
        }
    }
}
