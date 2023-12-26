package utils;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    private String name;
    private List<String> listOfFilters = new ArrayList<>();

    public List<String> getListOfFilters() {
        return listOfFilters;
    }

    public void setListOfFilters(List<String> list) {
        this.listOfFilters = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
