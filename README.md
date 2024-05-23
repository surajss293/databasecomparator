Request URL: localhost:8080/utility/databasecomparator

Request Type: POST

Request Payload Sample:
{
  
    "url1": "jdbc:mysql://localhost:3306",
    "user1": "root",
    "password1": "xxxxxxxxxx",
    "schema1": "db1",
    "table1": "table1",
    "url2":  "jdbc:mysql://localhost:3306",
    "user2": "root",
    "password2": "Suraj@74644",
    "schema2": "db2",
    "table2": "table2",
    "columns":["sr_no","name","state","pincode"]
  
}


Response Payload Sample:

{
    "commonRowsInBothTable": {
        "pincode": [],
        "sr_no": [],
        "name": [],
        "state": []
    },
    "onlyRowsInTable1": {
        "pincode": [],
        "sr_no": [],
        "name": [],
        "state": []
    },
    "onlyRowsInTable2": {
        "pincode": [],
        "sr_no": [],
        "name": [],
        "state": []
    }
}

