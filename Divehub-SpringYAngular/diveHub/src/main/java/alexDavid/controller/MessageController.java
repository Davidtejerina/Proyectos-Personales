package alexDavid.controller;

import alexDavid.service.MessageService.MessageService;
import alexDavid.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("divehub/messages")
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/allByUser/{email}")
    public ResponseEntity<List<Message>> getAllMessagesByUser(
            @PathVariable String email
    ){
        return ResponseEntity.ok(messageService.findByUser(email));
    }

    @PostMapping("/addMessage")
    public ResponseEntity<Message> postMessage(
            @RequestBody Message message
    ){
        return ResponseEntity.ok(messageService.addMessage(message));
    }
}
