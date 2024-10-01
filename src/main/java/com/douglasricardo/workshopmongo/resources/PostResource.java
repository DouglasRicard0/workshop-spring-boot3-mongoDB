package com.douglasricardo.workshopmongo.resources;






import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglasricardo.workshopmongo.domain.Post;
import com.douglasricardo.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Post>> findById(@PathVariable String id) {
		Optional<Post> obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = com.douglasricardo.workshopmongo.resources.util.URL.decodeParam(text);
		Date min = com.douglasricardo.workshopmongo.resources.util.URL.convertDate(minDate, new Date(0L));
		Date max = com.douglasricardo.workshopmongo.resources.util.URL.convertDate(maxDate, null);
		List<Post> list = service.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(list);

	}
}