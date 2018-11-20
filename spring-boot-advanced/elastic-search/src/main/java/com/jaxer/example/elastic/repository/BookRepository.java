package com.jaxer.example.elastic.repository;

import com.jaxer.example.elastic.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by jaxer on 2018/11/20
 */
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
}
