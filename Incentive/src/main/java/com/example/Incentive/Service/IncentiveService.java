package com.example.Incentive.Service;

import com.example.Incentive.Dto.IncentiveDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface IncentiveService {
    Map<String, Object> addIncentive(IncentiveDto incentiveDto) throws JsonProcessingException;
}
