package com.holyboom.flyer.gson;

/**
 * Created by flyer on 15/2/2.
 */
public class App {

    String id,version,name;
    App(){

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
