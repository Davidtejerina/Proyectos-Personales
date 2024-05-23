package alexDavid.service.DetailService;

import alexDavid.models.Activity;
import alexDavid.models.Detail;
import alexDavid.models.Item;
import alexDavid.models.Product;
import alexDavid.repository.ActivityRepository;
import alexDavid.repository.DetailRepository;
import alexDavid.repository.ItemRepository;
import alexDavid.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;
    private final ActivityRepository activityRepository;

    @Override
    public List<Detail> findByUser(String email) {
        return detailRepository.findByOrder_User_Email(email);
    }

    @Override
    public List<Detail> findByOrder(Long id) {
        return detailRepository.findByOrder_Id(id);
    }

    @Override
    public Detail addDetail(Detail detail) {
        Detail newDetail = detailRepository.save(detail);
        Product p = productRepository.findById(detail.getProductId()).orElseThrow();
        if(p.getCategory().toString().equals("PRODUCT")){
            Item i = itemRepository.findById(detail.getProductId()).orElseThrow();
            i.setStock(i.getStock()-detail.getQuantity());
            itemRepository.save(i);
        }else{
            Activity a = activityRepository.findById(detail.getProductId()).orElseThrow();
            a.setAvailable_spaces(a.getAvailable_spaces()-1);
            if(a.getAvailable_spaces()==0) a.setAvailable(false);
            activityRepository.save(a);
        }
        return newDetail;
    }

    @Override
    public void deleteByUser(String email) {
        this.findByUser(email);
        detailRepository.deleteByOrder_User_Email(email);
    }
}