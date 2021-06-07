package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.ConnectedUser;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.ConnectedUserRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class CommentServiceImpl  implements ICommentService{

	@Autowired
	CommentRepository  commentrepository;
	@Autowired
	ConnectedUserRepository  userRepository  ;
	
	
	public Comment AddComment(Comment comment,long idUser) {
		
		ConnectedUser  user= (ConnectedUser) userRepository.findById(idUser).get();
		 
		 comment.setUserId(user);
		
		commentrepository.save(comment);	
	
		return comment;
	}
	

	
	
	@Override
	public List<Comment> retrieveAllComments() {
		List<Comment> comments=(List<Comment>) commentrepository.findAll();
		return comments;	
		}
	
	
	@Override
	public Comment retrieveCommentById(Long id) {
	Comment comment= commentrepository.findById(id).get();
		 
		
		return comment;	
		}
	

	@Override
	public Comment updateComment(Comment comment) {
		commentrepository.save(comment);
		return comment;
	}

	@Override
	public void deleteComment(Long IdComment) {
		commentrepository.deleteById(IdComment);
	}

	
}
