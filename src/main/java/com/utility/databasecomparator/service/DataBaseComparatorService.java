package com.utility.databasecomparator.service;

import com.utility.databasecomparator.dto.ComparedDataResponseDTO;
import com.utility.databasecomparator.dto.DataBaseDetailsRequestDTO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface DataBaseComparatorService {
    public ComparedDataResponseDTO getDataCompareDetails(DataBaseDetailsRequestDTO dataBaseDetailsRequestDTO) throws SQLException;
}
