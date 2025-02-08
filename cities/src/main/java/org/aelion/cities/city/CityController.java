package org.aelion.cities.city;

import org.aelion.cities.city.CityService.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping(value = "api/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping
    public List<City> getAll(){
        return service.getAll();
    }

    @GetMapping("/{code}")
    public City getAll(@PathVariable String code){
        return service.getByCode(code);
    }
}
