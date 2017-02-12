/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author vasil
 * @param <T> Тип класса
 * @param <K> Тип ID
 */
public interface beanDAOInterface<T, K> {

    /**
     *
     * @param id Уникальный код объекта
     * @return возвращает объект
     */
    public T getItemById(K id);

    /**
     *
     * @return
     */
    public List<T> getItemList();

    /**
     *
     * @param startIndex
     * @param itemCount
     * @return
     */
    public List<T> getItemList(K startIndex, K itemCount);

    public long addItem(T Item);

    public boolean deleteItem(T Item);
    
    public boolean updateItem(T Item);

}
