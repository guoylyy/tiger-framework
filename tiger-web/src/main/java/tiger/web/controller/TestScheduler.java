/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tiger.biz.code.support.CodeManager;
import tiger.core.domain.CodeDomain;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 7:16 PM yiliang.gyl Exp $
 */
@Component
public class TestScheduler {

    @Autowired
    CodeManager codeManager;

    @Scheduled(cron = "0 0/1 20 * * ?")
    public void schedulerSmsScaner(){
        CodeDomain codeDomain = new CodeDomain();
        codeManager.createCode(codeDomain);
    }
}
