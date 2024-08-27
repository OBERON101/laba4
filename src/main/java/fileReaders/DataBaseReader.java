package fileReaders;

import models.Reactor;
import models.ReactorType;
import models.RegionMap;

import java.io.File;
import java.sql.*;
import java.util.*;

public class DataBaseReader {
    public static Map<String, List<Reactor>> importReactors(File file, Map<String, ReactorType> reactorTypes) throws SQLException {

        Map<String, List<Reactor>> reactorsByCountry = new TreeMap<>();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath())) {
            if (connection != null) {
                String reactorQuery = "SELECT * FROM reactors";
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(reactorQuery)) {

                    while (rs.next()) {
                        Reactor reactor = new Reactor(
                                rs.getString("name"),
                                rs.getString("country"),
                                reactorTypes.get(rs.getString("type")),
                                rs.getString("owner"),
                                rs.getString("operator"),
                                rs.getString("status"),
                                rs.getInt("thermalCapacity"),
                                rs.getInt("firstGridConnection"),
                                rs.getInt("suspendedDate"),
                                rs.getInt("permanentShutdownDate"));

                        reactorsByCountry.computeIfAbsent(reactor.getCountry(), k -> new ArrayList<>()).add(reactor);
                    }
                }

                String loadFactorQuery = "SELECT * FROM load_factors";
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(loadFactorQuery)) {

                    while (rs.next()) {
                        String name = rs.getString("name");
                        Integer year = rs.getInt("year");
                        Double loadFactor = rs.getDouble("loadFactor");


                        reactorsByCountry
                                .values()
                                .stream()
                                .flatMap(List::stream)
                                .filter(reactor -> reactor.getName().equals(name))
                                .findFirst()
                                .ifPresent(reactor -> reactor.addLoadFactor(year, loadFactor));
                    }
                }
            }

            reactorsByCountry
                    .values()
                    .stream()
                    .flatMap(List::stream)
                    .forEach(Reactor::fixLoadFactors);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }

        return reactorsByCountry;
    }

    public static RegionMap importRegions(File file) throws SQLException {
        RegionMap regionMap = new RegionMap();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath())) {
            if (connection != null) {
                String regionsQuery = "SELECT * FROM countries";
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery(regionsQuery)) {
                    while (rs.next()) {
                        regionMap.addCountry(
                                rs.getString("region"),
                                rs.getString("country"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }

        return regionMap;
    }
}
