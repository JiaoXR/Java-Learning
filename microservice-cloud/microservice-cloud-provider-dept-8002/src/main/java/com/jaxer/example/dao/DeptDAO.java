package com.jaxer.example.dao;

import com.jaxer.example.domain.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jaxer on 2018/10/31
 */
@Mapper
public interface DeptDAO {
    boolean insert(Dept dept);

    Dept findById(Integer id);

    List<Dept> findAll();
}
