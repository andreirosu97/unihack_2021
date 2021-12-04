package com.unihack.votefortimisoara.services.interfaces;

import java.util.List;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/4/2021, Sat
 **/

public interface EnhancedRestInterface <T, K> {
    T addChildToParent(final Long parentId, final Long childId) throws IllegalAccessException, NoSuchFieldException;
    T addChildrenToParent(final Long parentId, final List<Long> childrenIds) throws IllegalAccessException, NoSuchFieldException;
    K getChildFromParent(final Long parentId, final Long childId) throws IllegalAccessException, NoSuchFieldException;
    List<K> getChildrenFromParent(final Long parentId) throws IllegalAccessException, NoSuchFieldException;
}
