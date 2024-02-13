package org.aelion.cities.city.Impl;

import org.aelion.cities.city.City;
import org.aelion.cities.city.CityRepository;
import org.aelion.cities.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository repository;

    @Override
    public List<City> getAll() {
        return repository.findAll();
    }

    @Override
    public City getByCode(String code) {
        return repository.findById(code).orElseThrow();
    }
}
