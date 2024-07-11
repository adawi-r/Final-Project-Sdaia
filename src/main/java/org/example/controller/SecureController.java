package org.example.controller;

import jakarta.ws.rs.*;

@Path("secures")
public class SecureController {

    @GET
    public String getIt() {
        return "Success";
    }
}
