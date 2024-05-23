package com.utility.databasecomparator.controller;
import com.utility.databasecomparator.dto.ComparedDataResponseDTO;
import com.utility.databasecomparator.service.DataBaseComparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utility.databasecomparator.dto.DataBaseDetailsRequestDTO;

import java.sql.*;


@RestController
@RequestMapping("/utility")
public class DatabaseComparator {
    @Autowired
    DataBaseComparatorService dataBaseComparatorService;

    @PostMapping("/databasecomparator")
    public ResponseEntity<ComparedDataResponseDTO> databaseComparator(@RequestBody DataBaseDetailsRequestDTO dataBaseDetailsRequestDTO) throws SQLException {

        ComparedDataResponseDTO comparedDataResponseDTO=dataBaseComparatorService.getDataCompareDetails(dataBaseDetailsRequestDTO);

        return new ResponseEntity<>(comparedDataResponseDTO, HttpStatus.OK);

    }
    @GetMapping("/get")
    public String get(){
        return "hello";
    }
}
