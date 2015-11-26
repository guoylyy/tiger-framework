/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 7:16 PM yiliang.gyl Exp $
 */
@Component
public class TestScheduler {

    @Scheduled(fixedRate = 5000)
    public void schedulerSmsScaner(){
        System.out.println("开始短信扫描任务");
    }
}
