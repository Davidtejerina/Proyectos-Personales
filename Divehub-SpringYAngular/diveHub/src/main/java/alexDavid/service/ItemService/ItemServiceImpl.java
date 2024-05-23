package alexDavid.service.ItemService;

import alexDavid.models.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import alexDavid.repository.ItemRepository;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item save(Item item){
        return itemRepository.save(item);
    }

    @Override
    public List<Item> findProductsByTagIgnoreCase(String tag) {
        return itemRepository.findProductsByTagIgnoreCase(tag);
    }

    @Override
    public List<Item> findByNameContainsIgnoreCase(String name) {
        return itemRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public void deleteProductById(Long id) {itemRepository.deleteById(id);
    }

    @Override
    public Item update(Long id, Item item) {
        Item itemUpdated = this.findById(id);

        itemUpdated.setName(item.getName());
        itemUpdated.setDescription(item.getDescription());
        itemUpdated.setStarting_price(item.getStarting_price());
        itemUpdated.setFinal_price(item.getFinal_price());
        itemUpdated.setImage(item.getImage());
        itemUpdated.setCategory(item.getCategory());
        itemUpdated.setTag(item.getTag());
        itemUpdated.setWeight(item.getWeight());
        itemUpdated.setStock(item.getStock());

        return itemRepository.save(itemUpdated);
    }

    @Override
    public Boolean hasStock(Long id) {
        return itemRepository.findById(id).orElseThrow().getStock() > 0;
    }


    @Override
    public List<Item> findAllByOrderByFinal_priceDesc() {
        return itemRepository.findAllOrderByFinal_price();
    }

    @Override
    public List<Item> findAllByOrderByFinal_priceAsc() {
        return itemRepository.findAllOrderByFinal_priceAsc();
    }
}
