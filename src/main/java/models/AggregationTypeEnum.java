package models;

public enum AggregationTypeEnum {
    NOT_CHOSEN("Choose aggregation type"),
    BY_COUNTRY("Aggregate by country"),
    BY_OPERATOR("Aggregate by operator"),
    BY_REGION("Aggregate by region");

    private final String name;

    AggregationTypeEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
