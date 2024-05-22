package com.utility.databasecomparator.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DataBaseDetailsRequestDTO {

    String url1;
    String user1;
    String password1;
    String schema1;
    String table1;

    String url2;
    String user2;
    String password2;
    String schema2;
    String table2;

    List<String> columns;

}
