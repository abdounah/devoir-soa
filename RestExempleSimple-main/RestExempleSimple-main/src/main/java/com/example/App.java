package com.example;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.net.URI;

public class App {

    public static void main(String[] args) {
        URI baseUri = URI.create("http://localhost:2222/");
        ResourceConfig config = new ResourceConfig()
                .packages("com.example")
                .register(JacksonFeature.class)
                .register(CORSFilter.class); // Ajoutez cette ligne pour enregistrer le filtre CORS

        GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Jersey app started at " + baseUri);
        System.out.println(connectionDB.ConnectionDb.getFactory());
    }
}
