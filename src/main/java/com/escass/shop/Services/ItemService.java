package com.escass.shop.Services;

import com.escass.shop.Entities.ItemEntity;
import com.escass.shop.Repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price) {
        if (title == null || price == null) {
            throw new RuntimeException("둘 다 넣으세요");
        }
        if (price < 0) {
            throw new RuntimeException("음수 넣지 마세요");
        }
        if (price > 1000000000) {
            throw new RuntimeException("값이 너무 커요");
        }
        if (title.length() > 10) {
            throw new RuntimeException("너무 길어요");
        }
        ItemEntity item = new ItemEntity();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public Optional<ItemEntity> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public void updateItemById(Long id, String title, Integer price) {
        if (title == null || price == null) {
            throw new RuntimeException("둘 다 넣으세요");
        }
        if (price < 0) {
            throw new RuntimeException("음수 넣지 마세요");
        }
        if (price > 1000000000) {
            throw new RuntimeException("값이 너무 커요");
        }
        if (title.length() > 10) {
            throw new RuntimeException("너무 길어요");
        }
        Optional<ItemEntity> item = itemRepository.findById(id);
        if (item.isPresent()) {
            ItemEntity itemEntity = item.get();
            itemEntity.setTitle(title);
            itemEntity.setPrice(price);
            itemRepository.save(itemEntity);
        }
    }
}
