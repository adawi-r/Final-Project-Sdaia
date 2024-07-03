package org.example.services‎;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class SessionService implements Serializable {

    private int count;

    public int getCount() {
        return ++count;
    }

    @PostConstruct
    public void init(){
        System.out.println("Session object created");
    }

    @PreDestroy
    public void killed(){
        System.out.println("Session object kiilled");
    }

}
