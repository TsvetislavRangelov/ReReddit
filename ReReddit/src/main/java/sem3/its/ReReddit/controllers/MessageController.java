package sem3.its.ReReddit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sem3.its.ReReddit.domain.Message;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final SimpMessagingTemplate template;

    @PostMapping
    public ResponseEntity<Void> sendMessageToUser(@RequestBody Message message){
        template.convertAndSend("/topic/messages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
