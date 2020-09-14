package com.bruno.cursomongo.config;

import com.bruno.cursomongo.domain.Post;
import com.bruno.cursomongo.domain.User;
import com.bruno.cursomongo.dto.AuthorDTO;
import com.bruno.cursomongo.dto.CommentDto;
import com.bruno.cursomongo.repository.PostRepository;
import com.bruno.cursomongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

@Configuration
public class Instant implements CommandLineRunner {

    @Autowired
    UserRepository uRep;

    @Autowired
    PostRepository postRep;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        uRep.deleteAll();
        postRep.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmial.com");
        User bob = new User(null, "Bob Green", "bob@gmail.com");
        uRep.saveAll(Arrays.asList(maria, bob));

        Post post1 = new Post(null, sdf.parse("21/02/2020"), "Partiu Viagem!", "Indo para Sao Paulo", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/02/2020"), "Bom Dia!", "Trabalhando", new AuthorDTO(maria));

        CommentDto c1 = new CommentDto("Boa viagem!", sdf.parse("21/03/2020"), new AuthorDTO(bob));
        CommentDto c2 = new CommentDto("Aproveite", sdf.parse("24/03/2020"), new AuthorDTO(maria));
        CommentDto c3 = new CommentDto("Tenha um ótimo dia!", sdf.parse("30/02/2020"), new AuthorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().add(c3);

        postRep.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        uRep.save(maria);
    }
}
