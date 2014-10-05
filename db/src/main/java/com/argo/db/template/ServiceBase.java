package com.argo.db.template;

import com.argo.core.exception.EntityNotFoundException;
import com.argo.core.exception.ServiceException;

/**
 * Created by yaming_deng on 14-8-28.
 */
public interface ServiceBase<T> {
    /**
     * 读取详情
     * @param oid
     * @return
     * @throws com.argo.core.exception.ServiceException
     */
    T findById(Long oid)throws EntityNotFoundException;

    /**
     * 添加记录
     * @param entity
     * @return
     * @throws com.argo.core.exception.ServiceException
     */
    Long add(T entity) throws ServiceException;

    /**
     * 更新记录
     * @param entity
     * @return
     * @throws ServiceException
     */
    boolean update(T entity) throws ServiceException;

    /**
     * 移除记录.
     * @param oid
     * @return
     * @throws ServiceException
     */
    boolean remove(Long oid) throws ServiceException;
}
