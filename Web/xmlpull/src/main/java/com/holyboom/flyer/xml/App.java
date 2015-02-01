package com.holyboom.flyer.xml;

/**
 * Created by flyer on 15/2/1.
 */
public class App {
    String id,name,version;
    App(){

    }
    App(String id,String name,String version){
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
