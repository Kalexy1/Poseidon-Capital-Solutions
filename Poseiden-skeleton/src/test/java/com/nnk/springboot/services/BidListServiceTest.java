package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    private BidList bidList;

    @BeforeEach
    void setUp() {
        bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Test Account");
        bidList.setType("Type 1");
        bidList.setBidQuantity(100.0);
    }

    @Test
    void testGetAllBidLists() {
        when(bidListRepository.findAll()).thenReturn(Arrays.asList(bidList));

        List<BidList> bidLists = bidListService.getAllBidLists();

        assertNotNull(bidLists);
        assertEquals(1, bidLists.size());
        assertEquals("Test Account", bidLists.get(0).getAccount());

        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void testGetBidListById_BidListFound() {
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        BidList foundBidList = bidListService.getBidListById(1);

        assertNotNull(foundBidList);
        assertEquals("Test Account", foundBidList.getAccount());

        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    void testGetBidListById_BidListNotFound() {
        when(bidListRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> bidListService.getBidListById(2));

        assertEquals("BidList non trouvé", exception.getMessage());
        verify(bidListRepository, times(1)).findById(2);
    }

    @Test
    void testSaveBidList() {
        when(bidListRepository.save(any(BidList.class))).thenReturn(bidList);

        BidList savedBidList = bidListService.saveBidList(bidList);

        assertNotNull(savedBidList);
        assertEquals("Test Account", savedBidList.getAccount());

        verify(bidListRepository, times(1)).save(bidList);
    }

    @Test
    void testUpdateBidList() {
        BidList updatedBidListDetails = new BidList();
        updatedBidListDetails.setAccount("Updated Account");
        updatedBidListDetails.setType("Updated Type");
        updatedBidListDetails.setBidQuantity(200.0);

        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));
        when(bidListRepository.save(any(BidList.class))).thenReturn(updatedBidListDetails);

        BidList updatedBidList = bidListService.updateBidList(1, updatedBidListDetails);

        assertNotNull(updatedBidList);
        assertEquals("Updated Account", updatedBidList.getAccount());
        assertEquals(200.0, updatedBidList.getBidQuantity());

        verify(bidListRepository, times(1)).findById(1);
        verify(bidListRepository, times(1)).save(bidList);
    }

    @Test
    void testDeleteBidList() {
        doNothing().when(bidListRepository).deleteById(1);

        bidListService.deleteBidList(1);

        verify(bidListRepository, times(1)).deleteById(1);
    }
}
