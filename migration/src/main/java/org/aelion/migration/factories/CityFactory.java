package org.aelion.migration.factories;

import org.aelion.migration.dto.CityDto;

import java.util.ArrayList;
import java.util.List;

public class CityFactory {
    public static List<CityDto> generate() {
        List<CityDto> result = new ArrayList<CityDto>();

        CityDto city1 = new CityDto();
        city1.setInseeCode("34172");
        city1.setPostalCode("34000");
        city1.setName("montpellier");

        result.add(city1);

        CityDto city2 = new CityDto();
        city2.setInseeCode("31555");
        city2.setPostalCode("31400");
        city2.setName("toulouse");

        result.add(city2);

        CityDto city3 = new CityDto();
        city3.setInseeCode("75103");
        city3.setPostalCode("75003");
        city3.setName("paris");

        result.add(city3);

        CityDto city4 = new CityDto();
        city4.setInseeCode("13206");
        city4.setPostalCode("13006");
        city4.setName("marseille");

        result.add(city4);

        CityDto city5 = new CityDto();
        city5.setInseeCode("69388");
        city5.setPostalCode("69008");
        city5.setName("lyon");

        result.add(city5);

        CityDto city6 = new CityDto();
        city6.setInseeCode("06088");
        city6.setPostalCode("06100");
        city6.setName("nice");

        result.add(city6);

        CityDto city7 = new CityDto();
        city7.setInseeCode("35238");
        city7.setPostalCode("35000");
        city7.setName("rennes");

        result.add(city7);

        CityDto city8 = new CityDto();
        city8.setInseeCode("29019");
        city8.setPostalCode("29200");
        city8.setName("brest");

        result.add(city8);

        CityDto city9 = new CityDto();
        city9.setInseeCode("38185");
        city9.setPostalCode("38000");
        city9.setName("grenoble");

        result.add(city9);

        CityDto city10 = new CityDto();
        city10.setInseeCode("59350");
        city10.setPostalCode("59000");
        city10.setName("lille");

        result.add(city10);

        return result;
    }
}
