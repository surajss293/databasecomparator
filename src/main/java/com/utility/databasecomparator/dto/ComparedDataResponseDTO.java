package com.utility.databasecomparator.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class ComparedDataResponseDTO {
Map<String,Set<String>> commonRowsInBothTable;
Map<String,Set<String>> onlyRowsInTable1;
Map<String, Set<String>> onlyRowsInTable2;

}
