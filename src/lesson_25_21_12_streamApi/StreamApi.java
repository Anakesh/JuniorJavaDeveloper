package lesson_25_21_12_streamApi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class StreamApi {
    public static void main(String[] args) throws IOException {
        //создание stream
        Integer[] arr = {15,45,44,45,-12,0};
        Stream<Integer> arrStream = Arrays.stream(arr);

        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        Stream<Integer> listStream = list.stream();

        Stream<String> stringStream = Stream.of("wq","sa", "ds");

        File file = new File("wp.txt");
        Stream<String> fileStream = Files.lines(file.toPath());

        //примитивы
        IntStream intStream = IntStream.range(12,22);
        LongStream longStream = LongStream.builder()
                .add(3)
                .add(23)
                .add(66)
                .build();

        //удалить из списка элементы меньше 20
        // каждый элемент увеличить на 10
        // вывести в отсортированном порядке
        list = new ArrayList<>(Arrays.asList(arr));
        System.out.println(list);
        list.stream()
                .filter(i->i>20)
                .map(i->i+10)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println(list);

        Optional<Integer> optional = Stream.of(1,2,3,6)
                .filter(i->i<5)
                .findFirst();
        Integer i = optional.orElse(0);

        String res = Stream.of("wq","sa","kl")
                .reduce("", (k,j)->k+j);
        System.out.println(res);

        Integer ires = Stream.of(1,2,3)
                .reduce(0,(k,j)->k+j);
        System.out.println(ires);

        //top 10 words
        Map<String,Long> map = Files.lines(file.toPath())
                .parallel() //делаем стрим параллельным(будет использоваться пул потоков)
                .map(line->line.toLowerCase().replaceAll("\\pP"," "))
                .flatMap(line->Arrays.stream(line.split("\\s+")))
                .map(String::trim)
                .filter(word->!"".equals(word))
                .collect(                   //преобразование в любую колеекцию и соединяет потоки в единый
                        groupingBy(         //группирует коллекцию и возвращает map
                                identity(), //возвращает стри элементов коллекции(копию)
                                counting()  //возвращает стрим с количеством повотрений
                        ))
                .entrySet().parallelStream()    //получаем сет entry и бьем его на потоки
                .sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))
                .limit(10)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(map);

        Stream<String> wordStream = Files.lines(file.toPath()) //содержит набор методов, в нем ничего не посчитано
                .parallel() //делаем стрим параллельным(будет использоваться пул потоков)
                .map(line->line.toLowerCase().replaceAll("[\\W\\d]"," "))
                .flatMap(line->Arrays.stream(line.split("\\s+")))
                .map(String::trim)
                .filter(word->!"".equals(word));
        //the
//        Long count = wordStream
//                .filter("the"::equals)
//                .count();
//        System.out.println(count);


        //выбрать все элементы содерж the
        List<String> listRes =
                wordStream
                .filter((s->s.contains("the")))
                .collect(Collectors.toList());
        System.out.println(listRes);

        /*
        Методы .collect:
        .groupingBy - разделить коллекцию по условию и вернуть Map<N,List<T>>
        где T - тип последнего стрима
            N - значение разделителя

         */
        arr = new Integer[]{15, 45, 44, 45, -12, 0};
        arrStream = Arrays.stream(arr);
        IntSummaryStatistics statistics = arrStream
                .collect(Collectors.summarizingInt(p-> p-1));
        System.out.println(statistics);

        //Выбрать пользователей в возраст от 18 до 40
        //Найти макс возраст
        //отсортировать сначала по возрасту, потом по имени
        //Найтии средний возраст

        User user1 = new User("A",23);
        User user2 = new User("bS",44);
        User user3 = new User("C",2);
        User user4 = new User("Arwe",88);
        User user5 = new User("ggAwqer",30);
        User user6 = new User("Arweq",53);
        User user7 = new User("redfA",11);
        User user8 = new User("gdsfA",48);

        List<User> userList =Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8);
        List<User> ageSort = userList.stream().filter(u->(u.getAge()>18&&u.getAge()<40)).collect(Collectors.toList());
        System.out.println(ageSort);
        Integer maxAge = userList.stream().max(Comparator.comparingInt(User::getAge)).get().getAge();
        System.out.println(maxAge);
        IntSummaryStatistics iss = userList.stream().collect(Collectors.summarizingInt(u->u.getAge()));
        System.out.println(iss.getMax());
        System.out.println(iss.getAverage());

        userList.stream().mapToInt(u->u.getAge()).average();

        System.out.println(-7%2);

        //сумма всех нечетный
        list = new ArrayList<>(Arrays.asList(arr));
        Integer summ = list.stream().filter(num->num%2!=0).reduce((j,k)->j+k).get();

        // есть ли чимвол w в каждом имени пользователя

        boolean bool = userList.stream().allMatch(user -> user.getName().contains("w"));


    }
}

class User{
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Userrrrrr{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}