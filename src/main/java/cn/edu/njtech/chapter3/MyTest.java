package cn.edu.njtech.chapter3;

import cn.edu.njtech.entity.Author;
import cn.edu.njtech.entity.Book;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tim
 * @date 2022/11/5 18:41
 */
public class MyTest {

    private List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }

    @Test
    public void test1() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void test2() {
        List<Author> authors = getAuthors();
        Stream<Author> stream = authors.stream();

        Integer[] arr = {1, 2, 3, 4, 5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        Stream<Integer> stream2 = Stream.of(arr);


        Map<String, Integer> map = new HashMap<>();
        map.put("蜡笔小新", 19);
        map.put("黑子", 17);
        map.put("日向翔阳", 16);


        Stream<Map.Entry<String, Integer>> stream3 = map.entrySet().stream();
    }

    @Test
    public void test3() {
        List<Author> authors = getAuthors();

        authors.stream()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void test4() {
        List<Author> authors = getAuthors();

        authors.stream()
                .map(author -> author.getName())
                .forEach(name -> System.out.println(name));
    }

    @Test
    public void test5() {
        List<Author> authors = getAuthors();

        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(age -> System.out.println(age));
    }

    @Test
    public void test6() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author));
    }


    @Test
    public void test7() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(author.getAge()));
    }

    @Test
    public void test8() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(author -> System.out.println(author));
    }


    @Test
    public void test9() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .sorted()
                .limit(2)
                .forEach(author -> System.out.println(author));
    }

    @Test
    public void test10() {
        List<Author> authors = getAuthors();

        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author));
    }

    @Test
    public void test11() {
        List<Author> authors = getAuthors();

        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    @Test
    public void test12() {
        List<Author> authors = getAuthors();

        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();

        System.out.println(count);
    }

    @Test
    public void test13() {
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score1);
        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);

        System.out.println(max.get());
        System.out.println(min.get());
    }

    @Test
    public void test14() {
        List<Author> authors = getAuthors();
        List<String> nameList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());

        System.out.println(nameList);
    }

    @Test
    public void test15() {
        List<Author> authors = getAuthors();
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());

        System.out.println(books);
    }

    @Test
    public void test16() {
        List<Author> authors = getAuthors();

        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));

        System.out.println(map);
    }

    @Test
    public void test17() {
        List<Author> authors = getAuthors();

        boolean flag = authors.stream()
                .anyMatch(author -> author.getAge() > 29);

        System.out.println(flag);
    }

    @Test
    public void test18() {
        List<Author> authors = getAuthors();

        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() >= 18);

        System.out.println(flag);
    }

    @Test
    public void test19() {
        List<Author> authors = getAuthors();

        boolean flag = authors.stream()
                .noneMatch(author -> author.getAge() > 100);

        System.out.println(flag);
    }

    @Test
    public void test20() {
        List<Author> authors = getAuthors();

        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();

        System.out.println(optionalAuthor.get());
    }

    @Test
    public void test21() {
        List<Author> authors = getAuthors();

        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findFirst();

        System.out.println(optionalAuthor.get());
    }

    @Test
    public void test22() {
        List<Author> authors = getAuthors();

        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);

        System.out.println(sum);
    }

    @Test
    public void test23() {
        List<Author> authors = getAuthors();

        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (result, element) -> result > element ? result : element);

        System.out.println(sum);
    }


    @Test
    public void test24() {
        List<Author> authors = getAuthors();

        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (result, element) -> result < element ? result : element);

        System.out.println(sum);
    }

    @Test
    public void test25() {
        List<Author> authors = getAuthors();

        Optional<Integer> minOptional = authors.stream()
                .map(author -> author.getAge())
                .reduce((result, element) -> result > element ? element : result);

        minOptional.ifPresent(age -> System.out.println(age));
    }

}
