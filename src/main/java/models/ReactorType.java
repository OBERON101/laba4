package models;

import com.fasterxml.jackson.annotation.JsonAlias;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReactorType {
    private String type;
    @JsonAlias("class")
    private String reactorClass;
    @JsonAlias("burnup")
    private Double burnUp;
    @JsonAlias("electrical_capacity")
    private Double electricalCapacity;
    private Double enrichment;
    @JsonAlias("first_load")
    private Double firstLoad;
    private Double kpd;
    @JsonAlias("life_time")
    private Integer lifeTime;
    @JsonAlias("termal_capacity")
    private Double thermalCapacity;
    private Source source;

    public ReactorType() {
    }

    public ReactorType(String type, String reactorClass, Double burnUp, Double electricalCapacity, Double enrichment, Double firstLoad, Double kpd, Integer lifeTime, Double thermalCapacity, Source source) {
        this.type = type;
        this.reactorClass = reactorClass;
        this.burnUp = burnUp;
        this.electricalCapacity = electricalCapacity;
        this.enrichment = enrichment;
        this.firstLoad = firstLoad;
        this.kpd = kpd;
        this.lifeTime = lifeTime;
        this.thermalCapacity = thermalCapacity;
        this.source = source;
    }

    public static ReactorType buildFromMapAndSource(String type, Map<String, Object> map, Source source) {
        ReactorType reactorType = new ReactorType();
        reactorType.type = type;
        reactorType.reactorClass = (String) map.get("class");
        reactorType.burnUp = map.get("burnup").getClass() == Double.class ? (Double) map.get("burnup") : ((Integer) map.get("burnup")).doubleValue();
        reactorType.electricalCapacity = map.get("electrical_capacity").getClass() == Double.class ? (Double) map.get("electrical_capacity") : ((Integer) map.get("electrical_capacity")).doubleValue();
        reactorType.enrichment = map.get("enrichment").getClass() == Double.class ? (Double) map.get("enrichment") : ((Integer) map.get("enrichment")).doubleValue();
        reactorType.firstLoad = map.get("first_load").getClass() == Double.class ? (Double) map.get("first_load") : ((Integer) map.get("first_load")).doubleValue();
        reactorType.kpd = map.get("kpd").getClass() == Double.class ? (Double) map.get("kpd") : ((Integer) map.get("kpd")).doubleValue();
        reactorType.lifeTime = (Integer) map.get("life_time");
        reactorType.thermalCapacity = map.get("termal_capacity").getClass() == Double.class ? (Double) map.get("termal_capacity") : ((Integer) map.get("termal_capacity")).doubleValue();
        reactorType.source = source;
        return reactorType;
    }

    public List<DefaultMutableTreeNode> getTreeNodes() {
        List<DefaultMutableTreeNode> list = new ArrayList<>();
        DefaultMutableTreeNode reactorClass = new DefaultMutableTreeNode("reactorClass='" + this.reactorClass + "'");
        list.add(reactorClass);
        DefaultMutableTreeNode burnUp = new DefaultMutableTreeNode("burnUp=" + this.burnUp);
        list.add(burnUp);
        DefaultMutableTreeNode electricalCapacity = new DefaultMutableTreeNode("electricalCapacity=" + this.electricalCapacity);
        list.add(electricalCapacity);
        DefaultMutableTreeNode enrichment = new DefaultMutableTreeNode("enrichment=" + this.enrichment);
        list.add(enrichment);
        DefaultMutableTreeNode firstLoad = new DefaultMutableTreeNode("firstLoad=" + this.firstLoad);
        list.add(firstLoad);
        DefaultMutableTreeNode kpd = new DefaultMutableTreeNode("kpd=" + this.kpd);
        list.add(kpd);
        DefaultMutableTreeNode lifeTime = new DefaultMutableTreeNode("lifeTime=" + this.lifeTime);
        list.add(lifeTime);
        DefaultMutableTreeNode thermalCapacity = new DefaultMutableTreeNode("thermalCapacity=" + this.thermalCapacity);
        list.add(thermalCapacity);
        DefaultMutableTreeNode source = new DefaultMutableTreeNode("source=" + this.source.toString());
        list.add(source);

        return list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReactorClass() {
        return reactorClass;
    }

    public void setReactorClass(String reactorClass) {
        this.reactorClass = reactorClass;
    }

    public Double getBurnUp() {
        return burnUp;
    }

    public void setBurnUp(Double burnUp) {
        this.burnUp = burnUp;
    }

    public Double getElectricalCapacity() {
        return electricalCapacity;
    }

    public void setElectricalCapacity(Double electricalCapacity) {
        this.electricalCapacity = electricalCapacity;
    }

    public Double getEnrichment() {
        return enrichment;
    }

    public void setEnrichment(Double enrichment) {
        this.enrichment = enrichment;
    }

    public Double getFirstLoad() {
        return firstLoad;
    }

    public void setFirstLoad(Double firstLoad) {
        this.firstLoad = firstLoad;
    }

    public Double getKpd() {
        return kpd;
    }

    public void setKpd(Double kpd) {
        this.kpd = kpd;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Double getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(Double thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ReactorType{" +
                "type='" + type + '\'' +
                ", reactorClass='" + reactorClass + '\'' +
                ", burnUp=" + burnUp +
                ", electricalCapacity=" + electricalCapacity +
                ", enrichment=" + enrichment +
                ", firstLoad=" + firstLoad +
                ", kpd=" + kpd +
                ", lifeTime=" + lifeTime +
                ", thermalCapacity=" + thermalCapacity +
                ", source=" + source +
                '}';
    }
}
