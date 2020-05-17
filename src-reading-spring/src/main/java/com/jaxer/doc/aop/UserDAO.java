package com.jaxer.doc.aop;

import org.springframework.stereotype.Repository;

/**
 * Created on 2020/5/16 15:02
 *
 * @author jaxer
 */
@Repository
public class UserDAO {
    public void getById() {
        System.out.println("执行 UserDAO.getById");
    }
}
