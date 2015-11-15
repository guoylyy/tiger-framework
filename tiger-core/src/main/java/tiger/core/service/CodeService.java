/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.core.service;

import org.springframework.stereotype.Service;
import tiger.core.domain.CodeDomain;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:47 PM yiliang.gyl Exp $
 */
@Service
public class CodeService {

    public boolean persitentCode(CodeDomain codeDomain){
        System.out.println("start to persistent code");

        return true;
    }

}
