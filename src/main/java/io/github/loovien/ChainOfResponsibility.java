package io.github.loovien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * created 6/9/2021 10:37 PM
 *
 * @author luowen <loovien@163.com>
 */
public class ChainOfResponsibility {

    public interface Filter<T> {
        T doFilter(T t, FilterChain<T> fc);
    }

    public interface FilterChain<T> {
        FilterChain<T> addFilter(Filter<T> filter);

        T next(T t);
    }

    public static class HtmlFilter implements Filter<String> {
        @Override
        public String doFilter(String s, FilterChain<String> fc) {

            System.out.println("=======html filter ==========");
            String replace = s.replace("<", "#").replace(">", "#");
            String next = fc.next(replace);

            System.out.println("============= html filter ending==============");

            return next;
        }
    }

    public static class SensitiveFilter implements Filter<String> {

        @Override
        public String doFilter(String s, FilterChain<String> fc) {

            System.out.println("======= word filter ==========");
            String s1 = s.toUpperCase(Locale.ROOT);
            String next = fc.next(s1);

            System.out.println("============ word filter ending ===========");

            return next;
        }
    }

    public static class StringFilterClain implements FilterChain<String> {

        private int cursor = 0;

        private final List<Filter<String>> filters = new ArrayList<>();

        @Override
        public FilterChain<String> addFilter(Filter<String> filter) {
            filters.add(filter);
            return this;
        }

        @Override
        public String next(String string) {
            if (cursor + 1 > filters.size()) { // all filter execute
                cursor = 0;
                return string;
            }
            Filter<String> stringFilter = filters.get(cursor++);
            return stringFilter.doFilter(string, this);
        }
    }

    public static void main(String[] args) {
        String string = "<h1> hello this is a string need filter html tag and uppercase";
        StringFilterClain stringFilterClain = new StringFilterClain();
        stringFilterClain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
        System.out.println(string);
        System.out.println(stringFilterClain.next(string));
    }


}
