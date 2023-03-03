package com.example.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentObj {
    @Autowired
    private Environment env;

    public Float getInterestRate(String duration){
        Float interestRate=0f;
        if(Constant.DURATION_ONE_MONTH.equals(duration)){
            String configInterestRate = env.getProperty("config.interestRate.ONE_MONTH");
            interestRate = Float.parseFloat(configInterestRate);
        }else if(Constant.DURATION_TWO_MONTHS.equals(duration)){
            String configInterestRate = env.getProperty("config.interestRate.TWO_MONTHS");
            interestRate = Float.parseFloat(configInterestRate);
        }else if(Constant.DURATION_THREE_MONTHS.equals(duration)){
            String configInterestRate = env.getProperty("config.interestRate.THREE_MONTHS");
            interestRate = Float.parseFloat(configInterestRate);
        }else if(Constant.DURATION_ONE_YEAR.equals(duration)){
            String configInterestRate = env.getProperty("config.interestRate.ONE_YEAR");
            interestRate = Float.parseFloat(configInterestRate);
        }

        return interestRate;
    }
}
