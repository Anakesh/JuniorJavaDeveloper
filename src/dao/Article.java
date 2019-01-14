package dao;

public class Article {
    @Id
    int id;
    @DatabaseField
    String title;
    @DatabaseField
    int idUser;

    public Article(String title, int idUser) {
        this.title = title;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getIdUser() {
        return idUser;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
