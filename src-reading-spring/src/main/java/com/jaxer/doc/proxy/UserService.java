package com.jaxer.doc.proxy;

/**
 * Created on 2020/5/16 14:24
 *
 * @author jaxer
 */
public interface UserService {
    void getById(Integer id);

    boolean deleteById(Integer id);
}
