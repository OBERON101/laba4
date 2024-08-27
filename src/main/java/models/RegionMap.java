package models;

import java.util.*;

public class RegionMap {
    private final Map<String, List<String>> regions;

    public RegionMap() {
        regions = new HashMap<String, List<String>>();
    }

    public void addCountry(String region, String country) {
        if (!regions.containsKey(region)) {
            List<String> countryList = new ArrayList<>();
            countryList.add(country);
            regions.put(region, countryList);
        } else {
            regions.get(region).add(country);
        }
    }

    public Set<String> getRegionMap() {
        return new TreeSet<>(regions.keySet());
    }

    public List<String> getCountriesByRegion(String region) {
        List<String> sortedCountries = new ArrayList<>(regions.get(region));
        Collections.sort(sortedCountries);
        return sortedCountries;
    }

    public String getRegionByCountry(String country) {
        for (String region: regions.keySet()) {
            if (regions.get(region).contains(country)) {
                return region;
            }
        }
        return "";
    }

}
