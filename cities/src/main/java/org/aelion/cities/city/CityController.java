package org.aelion.cities.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("api/v1/cities")
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
