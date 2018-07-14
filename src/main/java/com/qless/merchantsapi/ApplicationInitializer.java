package com.qless.merchantsapi;

import com.qless.merchantsapi.services.MerchantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${bootstrap.restoreDB:false}")
    private boolean restoreDB;

    @Value("${bootstrap.fileName:null}")
    private String fileName;

    @Autowired
    private MerchantsService merchantsService;


    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        if (restoreDB) {
            merchantsService.restoreDatabase(fileName);
        }

        return;
    }

}