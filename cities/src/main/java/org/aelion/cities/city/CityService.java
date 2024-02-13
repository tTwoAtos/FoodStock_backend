package org.aelion.cities.city;

import java.util.List;

public interface CityService {
    List<City> getAll();
    City getByCode(String code);
}
