package cn.edu.njtech.chapter2;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @author tim
 * @date 2022/11/5 18:15
 */
public class MyTest {


    @Test
    public void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("你知道吗，我比你想象的 更想在你身边");
            }
        }).start();
    }

    @Test
    public void test2() {
        new Thread(() -> {
            System.out.println("你知道吗 我比你想象的 更想在你身边");
        }).start();
    }


    public int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

    @Test
    public void test3() {
        int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = calculateNum((left, right) -> {
            return left + right;
        });
        System.out.println(i);
    }


    public void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void test5() {
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });
    }

    @Test
    public void test6() {
        printNum((val) -> {
            return val % 2 == 0;
        });
    }


    public <R> R typeConver(Function<String, R> function) {
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    @Test
    public void test7() {
        Integer result = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });
        System.out.println(result);
    }

    @Test
    public void test8() {
        Integer result = typeConver((str) -> {
            return Integer.valueOf(str);
        });
        System.out.println(result);
    }


    public void foreachArr(IntConsumer consumer) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

    @Test
    public void test9() {
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
    }


    @Test
    public void test10() {
        foreachArr((val) -> {
            System.out.println(val);
        });
    }
}
