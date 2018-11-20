package com.jaxer.example.elastic;

import com.jaxer.example.elastic.domain.Book;
import com.jaxer.example.elastic.domain.User;
import com.jaxer.example.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ElasticsearchApplicationTests {
    private static final String ELASTIC_INDEX = "elastic";
    private static final String ELASTIC_TYPE = "user";

    @Autowired
    private JestClient jestClient; //方式一

    @Autowired
    private BookRepository bookRepository; // 方式二

    @Test
    public void springDataTest() {
        // 索引
        bookRepository.index(new Book(1, "《射雕英雄传》", "金庸"));

        // 查找（可自定义方法名查找）
        Iterable<Book> allBooks = bookRepository.findAll();
        for (Book book : allBooks) {
            log.info("book-->" + book);
        }
    }

    @Test
    public void jestIndex() {
        // 索引（保存）一个文档
        User user = new User(1, "Jack", 25, "1999-12-31");
        User user2 = new User(2, "Smith", 26, "1998-12-31");

        // 构建一个索引功能
        Index index = new Index.Builder(user2).index(ELASTIC_INDEX).type(ELASTIC_TYPE).build();
        try {
            // 执行
            DocumentResult result = jestClient.execute(index);
            log.info("result-->" + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jestSearch() {
        // 测试搜索功能
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"name\" : \"Jack\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search build = new Search.Builder(json).addIndex(ELASTIC_INDEX).addType(ELASTIC_TYPE).build();
        try {
            SearchResult searchResult = jestClient.execute(build);
            log.info("searchResult-->" + searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
