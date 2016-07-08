package model;

import model.versions.type.VersionsType;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that contains all <drug> tag children . Also contains specified wrapper method to add analogs in the list
 */
public class Drug {
    private String id;
    private String name;
    private String pharm;
    private GroupType group;
    private List<String> analogs;
    private VersionsType version;

    public Drug() {
        analogs=new ArrayList<>();
        version=new VersionsType();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPharm() {
        return pharm;
    }

    public GroupType getGroup() {
        return group;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public VersionsType getVersion() {
        return version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public void setGroup(GroupType group) {
        this.group = group;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public void addAnalog(String analog) {
        this.analogs.add(analog);
    }

    public void setVersion(VersionsType version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group=" + group +
                ", analogs=" + analogs +
                ", version=" + version +
                '}';
    }
}
