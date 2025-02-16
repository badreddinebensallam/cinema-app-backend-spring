package com.example.projetcinema;

import com.example.projetcinema.entities.Film;
import com.example.projetcinema.entities.Salle;
import com.example.projetcinema.entities.Ticket;
import com.example.projetcinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ProjetCinemaApplication implements CommandLineRunner {
    @Autowired
    private ICinemaInitService cinemaInitService;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(ProjetCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);
        cinemaInitService.initVilles();
     cinemaInitService.initCinemas();
     cinemaInitService.initSalles();
     cinemaInitService.initPlaces();
     cinemaInitService.initSeances();
     cinemaInitService.initCategories();
     cinemaInitService.initFilms();
     cinemaInitService.initProjections();
     cinemaInitService.initTickets();
    }
}
