package lesson_19_07_12_reflAnn.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//описание
//к аннотациям могут применяться мета-аннотации
//@Target() - указывает на сферу ответственности (METHOD,CONSTRUCTOR,FIELD,...)
//@Retention - указывает как долго хранить, к чему она относится и когда прекращает использоваться (когда работает)
//@Target(ElementType.FIELD) - только с полями
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionRequired {
//    все методы в аннотациязх без параметров и возвращают только:
//    строки, примитивы, enum' и другие аннотации
    User.Permission value();

}
