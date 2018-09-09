package com.jaxer.example.criteria;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/**
 * 分页条件查询
 * <p>
 * Created by jaxer on 08/09/2018
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PagedCriteria extends Criteria {
    protected Integer pageNo = 1;
    protected Integer pageSize = 10;
    protected Integer startPos = (pageNo - 1) * pageSize;
    protected Integer total = 0;
    protected Integer currentPage = (startPos / pageSize + startPos % pageSize);
    protected Integer totalPages = (total / pageSize + total % pageSize);
    protected Collection data;
}
