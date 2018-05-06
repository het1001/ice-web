package com.het.ice.service.scheduled;

import com.het.ice.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskServiceImpl {

    @Autowired
    private CommodityService commodityService;

    @Scheduled(cron="0 0 1 * * ?")
//    @Scheduled(cron="*/20 * * * * ?")
    public void resetSales(){
        commodityService.resetSales();
    }
}
