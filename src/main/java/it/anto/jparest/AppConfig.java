/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.anto.jparest;

import it.anto.jpaproj.services.EntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Anto
 */
@Configuration
@ComponentScan({"it.anto.jpaproj.services","it.anto.jparest.routes"})
public class AppConfig {

}
