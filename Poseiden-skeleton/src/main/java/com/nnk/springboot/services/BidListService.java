package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.domain.BidList;
import java.util.List;

@Service
public class BidListService {
    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> getAllBidLists() {
        return bidListRepository.findAll();
    }

    public BidList getBidListById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BidList non trouvé"));
    }

    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public BidList updateBidList(Integer id, BidList bidListDetails) {
        BidList bidList = getBidListById(id);
        bidList.setAccount(bidListDetails.getAccount());
        bidList.setType(bidListDetails.getType());
        bidList.setBidQuantity(bidListDetails.getBidQuantity());
        return bidListRepository.save(bidList);
    }

    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }
}
