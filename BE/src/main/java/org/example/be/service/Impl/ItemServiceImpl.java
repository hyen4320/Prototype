package org.example.be.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.be.security.dto.item.ReqItem;
import org.example.be.model.Item;
import org.example.be.repository.ItemRepository;
import org.example.be.repository.UserRepository;
import org.example.be.service.ItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    Long tmp=1L;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    @Override
    public void save(ReqItem reqItem) {
        Item item = Item.builder()
                .barcode(reqItem.getBarcode())
                .user_id(userRepository.findById(reqItem.getUser_id()).get())
                .build();
        itemRepository.save(item);


    }
}
