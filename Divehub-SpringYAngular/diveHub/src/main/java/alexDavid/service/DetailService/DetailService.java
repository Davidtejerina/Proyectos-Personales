package alexDavid.service.DetailService;

import alexDavid.models.Detail;
import java.util.List;


public interface DetailService {
    List<Detail> findByUser(String email);
    List<Detail> findByOrder(Long id);
    Detail addDetail(Detail detail);
    void deleteByUser(String email);
}