package utils;

import models.Reactor;
import models.RegionMap;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConsumptionCalc {
    private final Map<String, List<Reactor>> reactors;
    private RegionMap regions;

    public ConsumptionCalc(Map<String, List<Reactor>> reactors, RegionMap regions) {
        this.reactors = reactors;
        this.regions = regions;
    }

    public ConsumptionCalc(Map<String, List<Reactor>> reactors) {
        this.reactors = reactors;
    }

    public Map<Integer, Double> calcReactorConsumption(Reactor reactor) {
        Map<Integer, Double> consumptionPerYear = new TreeMap<>();

        reactor.getLoadFactors().keySet()
                .forEach(year -> consumptionPerYear.put(year, reactor.getThermalCapacity() /
                        reactor.getReactorType().getBurnUp()
                        * reactor.getLoadFactors().get(year) / 100000 * 365));

        return consumptionPerYear;
    }

    public Map<String, Map<Integer, Double>> calcConsumptionByCountries() {
        return calcConsumption(Reactor::getCountry);
    }

    public Map<String, Map<Integer, Double>> calcConsumptionByRegions() {
        return calcConsumption(reactor -> regions.getRegionByCountry(reactor.getCountry()));
    }

    public Map<String, Map<Integer, Double>> calcConsumptionByOperator() {
        return calcConsumption(Reactor::getOperator);
    }

    private Map<String, Map<Integer, Double>> calcConsumption(KeyExtractor keyExtractor) {
        Map<String, Map<Integer, Double>> consumption = new TreeMap<>();

        reactors.values()
                .stream()
                .flatMap(List::stream)
                .forEach(reactor -> {
                    String key = keyExtractor.extractKeyFromReactor(reactor);
                    Map<Integer, Double> entityConsumption = consumption.computeIfAbsent(key, k -> new TreeMap<>());
                    calcReactorConsumption(reactor)
                            .forEach((year, value) -> entityConsumption.merge(year, value, Double::sum));
                });

        return consumption;
    }

}
