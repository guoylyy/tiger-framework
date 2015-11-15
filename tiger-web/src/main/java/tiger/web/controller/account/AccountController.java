/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.controller.account;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tiger.web.controller.BaseController;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 8:40 PM yiliang.gyl Exp $
 */
@RestController
public class AccountController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        return new String("test success");
    }

}
