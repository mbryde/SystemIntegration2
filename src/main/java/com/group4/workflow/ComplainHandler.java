package com.group4.workflow;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("complainHandler")
public class ComplainHandler implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        // get variables
        //String customerId = externalTask.getVariable("customerId");
        //int creditScore = externalTask.getVariable("creditScore");

        // we could call an external service to create the loan documents here

        // complete the external task
        String customerId = externalTask.getVariable("field_118nu4r");
        externalTaskService.complete(externalTask);
        System.out.println(customerId);

        //Logger.getLogger("loanGranter")
                //.log(Level.INFO, "Loan for customer {0} with credit score {1} granted!", new Object[]{customerId, creditScore});
    }
}
