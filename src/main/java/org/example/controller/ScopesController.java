package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.example.services‎.ApplicationService;
import org.example.services‎.RequestService;
import org.example.services‎.SessionService;

@Path("scopes")
public class ScopesController {

    @Inject
    ApplicationService appServ;

    @Inject
    SessionService sessServ;

    @Inject
    RequestService reqServ;


    @GET
    public String getIt() {
        return "Got it: App: " + appServ.getCount() + ", Session: " + sessServ.getCount() + ", Request: " + reqServ.getCount();
    }
}
