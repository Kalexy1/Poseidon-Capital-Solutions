package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BidTests {

    @Autowired
    private BidListRepository bidListRepository;

    private BidList bid;

    @BeforeEach
    public void setup() {
    	bid = new BidList("Account Test", "Type Test", 10d, 5d, 100d, 105d);

        bid = bidListRepository.save(bid);
    }

    @AfterEach
    public void cleanup() {
        bidListRepository.deleteAll();
    }

    @Test
    public void testSaveBid() {
        assertNotNull(bid.getBidListId());
        assertEquals(10d, bid.getBidQuantity(), 0.001);
    }

    @Test
    public void testUpdateBid() {
        bid.setBidQuantity(20d);
        bid = bidListRepository.save(bid);
        assertEquals(20d, bid.getBidQuantity(), 0.001);
    }

    @Test
    public void testFindAllBids() {
        List<BidList> listResult = bidListRepository.findAll();
        assertFalse(listResult.isEmpty());
    }

    @Test
    public void testDeleteBid() {
        Integer id = bid.getBidListId();
        bidListRepository.delete(bid);
        Optional<BidList> bidList = bidListRepository.findById(id);
        assertFalse(bidList.isPresent());
    }
}
