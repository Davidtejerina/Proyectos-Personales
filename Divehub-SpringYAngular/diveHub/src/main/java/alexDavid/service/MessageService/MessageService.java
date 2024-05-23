package alexDavid.service.MessageService;

import alexDavid.models.Message;

public interface MessageService {
    java.util.List<Message> findByUser(String email);
    Message addMessage(Message message);
}
