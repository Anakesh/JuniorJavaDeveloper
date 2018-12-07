package lesson_19_07_12_reflAnn.reflectionAPI;


// reflection API


import lesson_9_07_11.Pair.Pair;

public class Child extends Parent{

    private String name;
    private int version = 1;
    private Parent parent;

    public Child(String name, int version){
        this.name = name;
        this.version = version;
    }

    public Child() {
    }

    private String getName(){
        return name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    private String getInfo(){
        return "private getInfo";
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}

class Parent{}
