package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.CommentServiceImpl;
import tn.esprit.spring.services.ICommentService;
import tn.esprit.spring.services.IUserService;

@RestController
public class commentRestControlleur{
	
	@Autowired
	
	IUserService iuserService;

	@Autowired
	
	ICommentService commentservice;

	@PostMapping("/add-Comment/{idUser}")
	@ResponseBody
	public Comment  AddComment(@RequestBody Comment  comment,@PathVariable("idUser") Long idUser ){
		
		
		return commentservice.AddComment(comment, idUser);
	}
	
	@GetMapping("/getAllComments")
	@ResponseBody
	public List<Comment> retrieveAllComments(){
		
	return 	commentservice.retrieveAllComments();
	}
	
	
	@GetMapping("/getCommentById/{id}")
	@ResponseBody
	public Comment retrieveCommentById(@PathVariable("id") Long id){
		
		return commentservice.retrieveCommentById(id);
	}
	
	
	
	@DeleteMapping("/deleteComment/{IdComment}")
	@ResponseBody
	public void deleteComment(@PathVariable("IdComment") Long IdComment){
		
		commentservice.deleteComment(IdComment);
	}
}