package homework_15_17_12_safeColl.bank;

public class User implements Comparable<User> {
    private int id;
    private String email;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }


    @Override
    public int compareTo(User o) {
        return this.id-o.id;
    }
}
