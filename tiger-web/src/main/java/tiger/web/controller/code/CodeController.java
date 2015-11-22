/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tiger.biz.code.support.CodeManager;
import tiger.common.util.StringUtil;
import tiger.web.form.CreateCodeForm;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 6:56 PM yiliang.gyl Exp $
 */
@RestController
public class CodeController {

    @Autowired
    private CodeManager codeManager;


    @RequestMapping(value="/code", method = RequestMethod.POST)
    public String createCode(@RequestBody CreateCodeForm createCodeForm){
        //1. 检测空值
        if(StringUtil.isBlank(createCodeForm.getTitle())){
           return new String("title can't be null");
        }
        //2. 创建代码对象
        boolean rc = codeManager.createCode(createCodeForm.convertToDomain());
        if(rc){
            System.out.println("create code success");
        }
        return new String(rc + "");
    }


}
