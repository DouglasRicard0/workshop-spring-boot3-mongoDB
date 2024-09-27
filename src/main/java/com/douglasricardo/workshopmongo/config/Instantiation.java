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
		Post post2 = new Post(null, sdf.parse("23/08/2018"), "Bom Dia", "Acrodei Feliz", new AuthorDTO(maria));

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		PostRepository.saveAll(Arrays.asList(post1, post2));
	}

}
