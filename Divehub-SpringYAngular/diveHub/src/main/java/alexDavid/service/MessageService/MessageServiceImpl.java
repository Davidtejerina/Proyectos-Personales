package alexDavid.service.MessageService;

import alexDavid.models.Message;
import alexDavid.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;

    @Override
    public List<Message> findByUser(String email) {
        return messageRepository.findAllByEmail(email);
    }

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }
}
