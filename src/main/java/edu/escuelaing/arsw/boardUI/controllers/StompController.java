package edu.escuelaing.arsw.boardUI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.boardUI.model.StringChange;

@Controller
public class STOMPController {
	
	@Autowired
	SimpMessagingTemplate msgt;

	@MessageMapping("/app/room.{roomFileId}")    
	public void handlePointEvent(StringChange strChange, @DestinationVariable String roomFileId) throws Exception {
		msgt.convertAndSend("/app/roomFile."+roomFileId, strChange);
	}

	@SubscribeMapping("/app/room.{roomFileId}")
	public String initialReply() throws Exception {
		return "hello there!";
	}

}