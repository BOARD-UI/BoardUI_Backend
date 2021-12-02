package edu.escuelaing.arsw.boardUI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import edu.escuelaing.arsw.boardUI.model.StringChange;

@Controller
public class STOMPController {
	
	@Autowired
	SimpMessagingTemplate msgt;

	/* Code editor STOMP controller */
	@CrossOrigin
	@MessageMapping("/app/roomFile.{fileId}")    
	public void handleChangeEvent(StringChange strChange, @DestinationVariable String fileId) throws Exception {
		msgt.convertAndSend("/app/roomFile."+fileId, strChange);
	}

	@CrossOrigin
	@SubscribeMapping("/app/roomFile.{fileId}")
	public String initialStringReply() throws Exception {
		return "hello there!";
	}


	/* Drawer STOMP controller */
	@CrossOrigin
	@MessageMapping("/app/roomCanvas.{fileId}")    
	public void handleDrawEvent(StringChange drawChange, @DestinationVariable String fileId) throws Exception {
		msgt.convertAndSend("/app/roomFile."+fileId, drawChange);
	}

	@CrossOrigin
	@SubscribeMapping("/app/roomCanvas.{fileId}")
	public String initialDrawReply() throws Exception {
		return "hello there!";
	}
	

}