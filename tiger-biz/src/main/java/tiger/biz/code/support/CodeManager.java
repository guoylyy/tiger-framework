/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz.code.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tiger.core.domain.CodeDomain;
import tiger.core.service.CodeService;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:36 PM yiliang.gyl Exp $
 */
@Service
public class CodeManager {


    @Autowired
    private CodeService codeService;

    public boolean createCode(CodeDomain codeDomain){
        System.out.println("start to create code");
        //1. 判断是否权限创建

        //2. 开始创建

        //3. 打包返回结果
        return codeService.persitentCode(codeDomain);
    }

}
