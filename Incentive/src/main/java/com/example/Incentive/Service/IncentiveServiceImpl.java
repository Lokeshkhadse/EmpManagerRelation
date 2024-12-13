package com.example.Incentive.Service;

import com.example.Incentive.Dao.IncentiveDao;
import com.example.Incentive.Dto.IncentiveDto;
import com.example.Incentive.Entity.Incentive;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IncentiveServiceImpl implements IncentiveService{

    @Autowired
    private IncentiveDao incentiveDao;
    @Override
    public Map<String, Object> addIncentive(IncentiveDto incentiveDto) throws JsonProcessingException {
        IncentiveDto incentiveDto1 = incentiveDao.addIncentive(incentiveDto);

        // Map stocks to only include `id` and `quantity`
        List<Map<String, Integer>> filteredStocks = incentiveDto1.getStocks().stream()
                .map(stock -> Map.of(
                        "id", stock.getId(),
                        "quantity", stock.getQuantity()
                ))
                .toList();

        // Create a new response structure
        Map<String, Object> filteredResponse = Map.of(
                "emp_id", incentiveDto1.getEmp_id(),
                "stocks", filteredStocks
        );


        return Map.of(
                "status", HttpStatus.OK.value(),
                "success","successfully added",
                "data",filteredResponse
        );
    }
}
