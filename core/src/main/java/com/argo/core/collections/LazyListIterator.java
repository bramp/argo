package com.argo.core.collections;

import com.argo.core.entity.EntityGetter;
import com.argo.core.exception.ServiceException;
import com.argo.core.utils.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;


/**
 * LazyList迭代器
 * @author yaming_deng
 *
 * @param <E>
 */
public class LazyListIterator<E> implements Iterator<E> {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Iterator<Long> itor = null;
    private Class<E> entityClass;
    private EntityGetter getter = null;

	public LazyListIterator(List<Long> itemIds) {
		
		super();
		
		Assert.notNull(itemIds);

		this.itor = itemIds.iterator();

        this.entityClass = ClassUtils.getT(getClass());
        this.getter = new EntityGetter(itemIds.get(0));
	}

	@Override
	public boolean hasNext() {
		return itor.hasNext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E next() {
		Long itemId = itor.next();
		try {
			E o = this.getter.get(this.entityClass);
            return o;
		} catch (ServiceException e) {
			log.error("读取实体详细错误:itemId="+itemId, e);
		}
		return null;
	}

	@Override
	public void remove() {
		this.itor.remove();
	}

}
