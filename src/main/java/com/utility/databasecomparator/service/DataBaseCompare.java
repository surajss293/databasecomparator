package com.utility.databasecomparator.service;

import com.utility.databasecomparator.dto.ComparedDataResponseDTO;
import com.utility.databasecomparator.dto.DataBaseDetailsRequestDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class DataBaseCompare implements DataBaseComparatorService{
    @Override
    public ComparedDataResponseDTO getDataCompareDetails(DataBaseDetailsRequestDTO dataBaseDetailsRequestDTO) throws SQLException {
        ComparedDataResponseDTO comparedDataResponseDTO=new ComparedDataResponseDTO();

        Connection conn1,conn2;
        conn1= DriverManager.getConnection(dataBaseDetailsRequestDTO.getUrl1()+"/"+ dataBaseDetailsRequestDTO.getSchema1(), dataBaseDetailsRequestDTO.getUser1(), dataBaseDetailsRequestDTO.getPassword1());
        conn2=DriverManager.getConnection(dataBaseDetailsRequestDTO.getUrl2()+"/"+ dataBaseDetailsRequestDTO.getSchema2(), dataBaseDetailsRequestDTO.getUser2(), dataBaseDetailsRequestDTO.getPassword2());

        Statement statement1=conn1.createStatement();
        Statement statement2=conn2.createStatement();

        ResultSet res1=statement1.executeQuery("select * from "+ dataBaseDetailsRequestDTO.getTable1());
        ResultSet res2=statement2.executeQuery("select * from "+ dataBaseDetailsRequestDTO.getTable2());


        List<String> columns= dataBaseDetailsRequestDTO.getColumns();


        Map<String, Set<String>> mp1=new HashMap<>();
        Map<String,Set<String>> mp2=new HashMap<>();

        //Set<String> intersection = new HashSet<>();
        while (res1.next()) {
            for(int i=1;i<=columns.size();i++){
                if(!mp1.containsKey(columns.get(i-1))){
                    mp1.put(columns.get(i-1),new HashSet<>());
                }
                mp1.get(columns.get(i-1)).add(res1.getString(columns.get(i-1)));
            }
            //intersection.add(res1.getString("common_column"));
        }

        while (res2.next()) {
            for(int i=1;i<=columns.size();i++){
                if(!mp2.containsKey(columns.get(i-1))){
                    mp2.put(columns.get(i-1),new HashSet<>());
                }
                mp2.get(columns.get(i-1)).add(res2.getString(columns.get(i-1)));
            }
            //intersection.add(res1.getString("common_column"));
        }

        conn1.close();
        conn2.close();

        //Retains only  element that are only present in table1
        Map<String,Set<String>> onlyInTable1=new HashMap<>();
        for(String column:columns){
            onlyInTable1.put(column,mp1.get(column).stream().filter(items->!mp2.get(column).contains(items)).collect(Collectors.toSet()));
        }

        //Retains only  element that are only present in table2
        Map<String,Set<String>> onlyInTable2=new HashMap<>();
        for(String column:columns){
            onlyInTable2.put(column,mp2.get(column).stream().filter(items->!mp1.get(column).contains(items)).collect(Collectors.toSet()));
        }

        //Retain all elements that are only present in both table
        Map<String,Set<String>> commonInBothTables=mp1;
        for(String column:columns){
            commonInBothTables.get(column).retainAll(mp2.get(column));
        }

        comparedDataResponseDTO.setCommonRowsInBothTable(commonInBothTables);
        comparedDataResponseDTO.setOnlyRowsInTable1(onlyInTable1);
        comparedDataResponseDTO.setOnlyRowsInTable2(onlyInTable2);
        return comparedDataResponseDTO;
    }

}
