/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.anto.jparest.services;

/**
 *
 * @author Anto
 */
public interface IEntityRepository {

    <T> void createEntity(T entity);

    <T extends Object> T find(Long id, Class<T> clazz);

    <T extends Object> T findEntity(Long id, Class<T> clazz);
    
}
