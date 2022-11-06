package cn.edu.njtech.chapter4;

import cn.edu.njtech.entity.Author;
import cn.edu.njtech.entity.Book;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author tim
 * @date 2022/11/5 21:30
 */
public class MyTest {

    public static void main(String[] args) {
        Author author = getAuthor();

        Optional<Author> authorOptional = getAuthorOptional();

        authorOptional.ifPresent(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author);
            }
        });

        Author author1 = authorOptional.orElseGet(() -> new Author());

        try {
            Author authorTemp = authorOptional.orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new RuntimeException("author为空");
                }
            });
            System.out.println(authorTemp.getName());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        final Optional<Author> empty = Optional.empty();

        // Exception in thread "main" java.util.NoSuchElementException: No value present
        // System.out.println(empty.get());

        Optional<Author> authorOptional2 = Optional.ofNullable(getAuthor());
        authorOptional.filter(author2 -> author2.getAge() > 100).ifPresent(author2 -> System.out.println(author.getName()));


        Optional<Author> authorOptional3 = Optional.ofNullable(getAuthor());

        if (authorOptional3.isPresent()) {
            System.out.println(authorOptional.get().getName());
        }

        Optional<Author> authorOptional4 = getAuthorOptional();
        Optional<List<Book>> optionalBooks = authorOptional.map(author2 -> author2.getBooks());
        optionalBooks.ifPresent(books -> System.out.println(books));
    }

    private static Optional<Author> getAuthorOptional() {
        final Author author = new Author(1L, "余华", 56, "最喜欢的作家", null);
        return Optional.ofNullable(author);
    }

    private static Author getAuthor() {
        final Author author = new Author(1L, "余华", 56, "最喜欢的作家", null);
        return null;
    }
}
