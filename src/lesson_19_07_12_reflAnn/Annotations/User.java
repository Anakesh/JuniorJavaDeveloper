package lesson_19_07_12_reflAnn.Annotations;

public class User {
    public static enum Permission{
        USER, ADMIN
    }
    private Permission permission;
    public User(Permission permission){
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
