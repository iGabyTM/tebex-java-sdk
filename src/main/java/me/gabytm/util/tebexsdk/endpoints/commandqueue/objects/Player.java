package me.gabytm.util.tebexsdk.endpoints.commandqueue.objects;

public class Player {

    private int id;
    private String name;
    private String uuid;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Player @ {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
