package com.leo.controllers;

import com.leo.controllers.admin.AuditoriumManagerController;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ServiceRegistry {
    private AuditoriumManagerController auditoriumManagerController = new AuditoriumManagerController();
    public String handleRequest(String serviceName, LinkedHashMap object) throws SQLException {
        switch (serviceName) {
            case "CREATE_AUDITORIUM":
                // Implement the logic for the "create" operation of the "myService" service here
                return auditoriumManagerController.actionAdd(object);
            case "read":
                // Implement the logic for the "read" operation of the "myService" service here
                return "Response from MyServiceController for read operation";
            case "update":
                // Implement the logic for the "update" operation of the "myService" service here
                return "Response from MyServiceController for update operation";
            case "delete":
                // Implement the logic for the "delete" operation of the "myService" service here
                return "Response from MyServiceController for delete operation";
            default:
                throw new IllegalArgumentException("Invalid service name: " + serviceName);
        }
    }
}

