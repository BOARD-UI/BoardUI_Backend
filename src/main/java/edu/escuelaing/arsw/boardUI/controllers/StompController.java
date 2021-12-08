package edu.escuelaing.arsw.boardUI.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class StompController {
	
	@Autowired
	SimpMessagingTemplate msgt;

	ConcurrentHashMap<String, List<String>> roomFileHashMap = new ConcurrentHashMap<>();
	ConcurrentHashMap<String, List<String>> roomCanvasHashMap = new ConcurrentHashMap<>();
	

	/* Room STOMP controller */
	@CrossOrigin
	@MessageMapping("/app/room.{roomId}")    
	public String handleRoomEvent(Map<String, String> roomChange, @DestinationVariable String roomId) throws Exception {
		String response = "none";
		if (roomChange.get("type") == "update") response = "update";
		else if (roomChange.get("type") == "save"){
			if (roomChange.get("fileType") == "canvas") roomCanvasHashMap.put(roomChange.get("roomFileId"), new LinkedList<String>());
			else if (roomChange.get("fileType") == "file") roomFileHashMap.put(roomChange.get("roomFileId"), new LinkedList<String>());
		}
		return response;
	}

	/* Code editor STOMP controller */
	@CrossOrigin
	@MessageMapping("/app/roomFile.{roomFileId}")    
	public String handleChangeEvent(String strChange, @DestinationVariable String roomFileId) throws Exception {
		for(String key: roomFileHashMap.keySet()){
			System.out.println(key);
		}
		roomFileHashMap.get(roomFileId).add(strChange);
		return strChange;
	}

	@CrossOrigin
	@SubscribeMapping("/app/roomFile.{roomFileId}")
	public List<String> initialStringReply(@DestinationVariable String roomFileId) throws Exception {
		if (!roomFileHashMap.keySet().contains(roomFileId)) roomFileHashMap.put(roomFileId, new LinkedList<String>());
		for(String key: roomFileHashMap.keySet()){
			System.out.println(key);
		}
		return roomFileHashMap.get(roomFileId);
	}
	
	/* Drawer STOMP controller */
	@CrossOrigin
	@MessageMapping("/app/roomCanvas.{roomFileId}")    
	public String handleDrawEvent(String drawChange, @DestinationVariable String roomFileId) throws Exception {
		roomCanvasHashMap.get(roomFileId).add(drawChange);
		return drawChange;
	}

	@CrossOrigin
	@SubscribeMapping("/app/roomCanvas.{roomFileId}")
	public List<String> initialDrawReply(@DestinationVariable String roomFileId) throws Exception {
		
		if (!roomCanvasHashMap.contains(roomFileId)) roomCanvasHashMap.put(roomFileId, new LinkedList<String>());
		return roomCanvasHashMap.get(roomFileId);
	}

}