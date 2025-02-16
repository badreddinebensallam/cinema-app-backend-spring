package com.example.projetcinema.web;

import com.example.projetcinema.dao.FilmRepository;
import com.example.projetcinema.dao.TicketRepository;
import com.example.projetcinema.entities.Film;
import com.example.projetcinema.entities.Ticket;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping(path="/imageFilm/{id}",produces= MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name="id")Long id) throws Exception{
    Film f=filmRepository.findById(id).get();
    String photoName=f.getPhoto();
    File file=new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
    Path path= Paths.get(file.toURI());
    return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketFrom ticketFrom){
        List<Ticket> listTickets=new ArrayList<>();
       ticketFrom.getTickets().forEach(idTicket->{
           Ticket ticket=ticketRepository.findById(idTicket).get();
           ticket.setNomClient(ticketFrom.getNomClient());
           ticket.setReserve(true);
           ticket.setCodePayement(ticket.getCodePayement());
           ticketRepository.save(ticket);
           listTickets.add(ticket);
       });
        return listTickets;
    }
}

@Data
class TicketFrom{
    private String nomClient;
    private int codePayement;
    private List<Long> tickets=new ArrayList<>();
}