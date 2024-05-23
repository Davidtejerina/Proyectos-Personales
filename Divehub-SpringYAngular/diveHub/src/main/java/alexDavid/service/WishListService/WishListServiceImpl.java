package alexDavid.service.WishListService;

import alexDavid.models.WishList;
import alexDavid.repository.ItemRepository;
import alexDavid.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<WishList> getListByUser(String email){
        return this.wishListRepository.findByUser_Email(email);
    }

    @Override
    public void cleanWishList(String email){
        this.wishListRepository.deleteByUser_Email(email);
    }

    @Override
    public void save(WishList wishList) {
        this.wishListRepository.save(wishList);
    }

    @Override
    public void removeItem(Long itemId, String email) {
        wishListRepository.deleteByItem_IdAndUser_Email(itemId, email);
    }

    @Override
    public void removeActivity(Long activityId, String email) {
        wishListRepository.deleteByActivity_IdAndUser_Email(activityId, email);
    }

    @Override
    public void addProduct(WishList wishList){
        if(wishList.getItem()==null && wishList.getActivity()==null) return;
        wishListRepository.save(wishList);
    }

    @Override
    public Boolean isProductLiked(String email, Long itemId, Long activityId) {
        return wishListRepository.isItemOrActivityLiked(itemId, activityId, email);
    }

    @Override
    public Boolean isItem(Long productId) {
        return itemRepository.findById(productId).isPresent();
    }
}














