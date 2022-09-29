package com.group4.workflow;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("complainHandler")
public class ComplainHandler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        //Get info from form
        String customerId = externalTask.getVariable("field_118nu4r");

        /*String store = externalTask.getVariable("field_0qn5ai7");
        String timeOfEncounter = externalTask.getVariable("field_1ulwlue");

        String employeeName = externalTask.getVariable("field_0u50y34");
        String complain = externalTask.getVariable("field_1pftpzl");
        String email = externalTask.getVariable("field_1ufiy2w");*/

        //Make msg based on data from the form
        String msg="";
        //Customer
        if(externalTask.getVariable("field_0u50y34") != null){
            String employeeName = externalTask.getVariable("field_0u50y34");
            msg = "Dear " + customerId + ",\n\nThank you for your complaint. We will look into it and get back to you as soon as possible.\n\nBest regards,\nCustomer Service"+
                    "We were sad to hear about your experience with our employee" +employeeName+" we at ikea strive to give the best service possible and will evaluate if we need to take any action regarding this employee";
            writeToFile(msg,"EmployeeComplain");

        }

        //environment
        if(externalTask.getVariable("field_1kxa1je") != null){
            String complainInfoField = externalTask.getVariable("field_1kxa1je");
            msg = "Dear " + customerId + ",\n\nThank you for your complaint. We will look into it and get back to you as soon as possible.\n\nBest regards,\nCustomer Service"+
                    "We were sad to hear about your experience with our environment we at ikea strive to have the best environment possible and will evaluate if we need to take any action regarding this issue";
            writeToFile(msg,"EnvironmentComplain");

        }

        //Product
        if(externalTask.getVariable("field_0rvpr63") != null){
            String productType = externalTask.getVariable("field_0q6hkii");
            msg = "Dear " + customerId + ",\n\nThank you for your complaint. We will look into it and get back to you as soon as possible.\n\nBest regards,\nCustomer Service"+
                    "We were sad to hear about your experience with our product" +productType+" we at ikea strive to have the best quality possible and will evaluate if we need to take any action regarding this product";
            writeToFile(msg,"ProductComplain");
        }

        //Write to file


        externalTaskService.complete(externalTask);
        System.out.println(customerId);
    }

    public boolean writeToFile(String msg, String type) {
        //write to file
        try{
            File myObj = new File(type+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

    }
}
