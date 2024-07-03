package org.example.services‎;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationService {

    private int count;

    public int getCount() {
        return ++count;
    }
}
