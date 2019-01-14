package dao;

public class Userrrrrr {
    @Id
    int id;
    @DatabaseField
    String login;

    public Userrrrrr(){}

    public Userrrrrr(String login) {

        this.login = login;
    }

    @Override
    public String toString() {
        return "Userrrrrr{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
