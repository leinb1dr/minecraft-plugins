package com.drleinbach.service;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/23/13
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Service<T, ID> {
    Integer insert(T arg0);
    T get(ID arg0);
}
