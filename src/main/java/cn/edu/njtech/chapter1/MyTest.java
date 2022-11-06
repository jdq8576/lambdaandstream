package cn.edu.njtech.chapter1;

import cn.edu.njtech.entity.Author;
import cn.edu.njtech.entity.Book;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tim
 * @date 2022/11/5 00:34
 */
public class MyTest {

    List<Author> authors = new ArrayList<>();

    @Test
    public void test1() {
        //查询未成年作家的评分在70以上的书籍 由于洋流影响所以作家和书籍可能出现重复，需要进行去重
        List<Book> bookList = new ArrayList<>();
        Set<Book> uniqueBookValues = new HashSet<>();
        Set<Author> uniqueAuthorValues = new HashSet<>();
        for (Author author : authors) {
            if (uniqueAuthorValues.add(author)) {
                if (author.getAge() < 18) {
                    List<Book> books = author.getBooks();
                    for (Book book : books) {
                        if (book.getScore() > 70) {
                            if (uniqueBookValues.add(book)) {
                                bookList.add(book);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(bookList);
    }

    @Test
    public void test2() {
        List<Book> collect = authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .map(author -> author.getBooks())
                .flatMap(Collection::stream)
                .filter(book -> book.getScore() > 70)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
