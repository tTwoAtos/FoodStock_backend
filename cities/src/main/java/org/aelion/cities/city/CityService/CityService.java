package org.aelion.cities.city.CityService;

import org.aelion.cities.city.City;

import java.util.List;

public interface CityService {
    List<City> getAll();
    City getByCode(String code);
}
