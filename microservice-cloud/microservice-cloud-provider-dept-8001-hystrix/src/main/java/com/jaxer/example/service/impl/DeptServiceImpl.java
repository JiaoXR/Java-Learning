package com.jaxer.example.service.impl;

import com.jaxer.example.dao.DeptDao;
import com.jaxer.example.domain.Dept;
import com.jaxer.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jaxer on 2018/11/09
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean insert(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptDao.findById(id);
    }

    public List<Dept> findAll() {
        return deptDao.findAll();
    }
}
