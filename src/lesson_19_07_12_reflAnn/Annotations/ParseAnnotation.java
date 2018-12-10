package lesson_19_07_12_reflAnn.Annotations;

public class ParseAnnotation {
    public static void main(String[] args) {
        User admin = new User(User.Permission.ADMIN);
        Class<?> actionClass = DeleteAction.class;
        PermissionRequired permissionRequired = actionClass.getAnnotation(PermissionRequired.class);

        if(permissionRequired != null){
            if(admin.getPermission().equals(permissionRequired.value())){
                System.out.println("Пользователю доступно удаление");
            }
        }
    }
}

//Написать рефлексивный toString

//  написать небольщой Dependency Injection Framework
//Всю логику можно собрать в одном классе DIContext
//благодаря которому можно создавать экземпляр любого класса
//с автомотически установленными зависимостями
