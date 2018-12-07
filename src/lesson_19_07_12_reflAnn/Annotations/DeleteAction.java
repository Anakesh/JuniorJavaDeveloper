package lesson_19_07_12_reflAnn.Annotations;

//использование
@PermissionRequired(User.Permission.ADMIN)
public class DeleteAction {
    public void delete(User user){
        System.out.println("del user");
    }
}
