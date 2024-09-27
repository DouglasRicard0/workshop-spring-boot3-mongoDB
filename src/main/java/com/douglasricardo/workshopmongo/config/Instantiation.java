package com.douglasricardo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.douglasricardo.workshopmongo.domain.Post;
import com.douglasricardo.workshopmongo.domain.User;
import com.douglasricardo.workshopmongo.dto.AuthorDTO;
import com.douglasricardo.workshopmongo.dto.CommentDTO;
import com.douglasricardo.workshopmongo.repository.PostRepository;
import com.douglasricardo.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository PostRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		PostRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey Brown", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/08/2018"), "Vou viajar", "Vou viajar para São Paulo",new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/08/2018"), "Bom dia", "Acrodei Feliz", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa Viagem mano", sdf.parse("21/08/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite a Viagem", sdf.parse("22/08/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia", sdf.parse("23/08/2018"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post1.getComments().addAll(Arrays.asList(c3));

		PostRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}
