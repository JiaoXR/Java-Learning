package com.jaxer.example.service;

import com.jaxer.example.domain.Dept;

import java.util.List;

/**
 * Created by jaxer on 2018/10/31
 */
public interface DeptService {
    boolean insert(Dept dept);

    Dept findById(Integer id);

    List<Dept> findAll();
}
