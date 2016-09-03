package utils;

import entity.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2016/9/2.
 */
public class DBUtils {
    private static Map<String, Book> books = new HashMap<String, Book>();

    static {
        books.put("1", new Book("1", "金瓶梅1", 100, "大神1"));
        books.put("2", new Book("1", "金瓶梅2", 100, "大神2"));
        books.put("3", new Book("1", "金瓶梅3", 100, "大神3"));
        books.put("4", new Book("1", "金瓶梅4", 100, "大神4"));
    }

    //得到所有图书的列表
    public static Map<String, Book> getBooks() {
        return books;
    }

    //根据id找到指定的书
    public static Book getBookById(String id) {
        return books.get(id);
    }
}
