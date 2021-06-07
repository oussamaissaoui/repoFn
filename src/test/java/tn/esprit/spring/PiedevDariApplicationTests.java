package tn.esprit.spring;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.repository.CommentRepository;


@SpringBootTest
class PiedevDariApplicationTests {

	@Autowired
	private CommentRepository comentRepo;
	
	
	
	
	@Test
	void contextLoads() {
		
		
		
	}
	

}
