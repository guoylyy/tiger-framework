/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.biz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yiliang.gyl
 * @version $ID: v 0.1 9:53 PM yiliang.gyl Exp $
 */
@SpringBootApplication
@ComponentScan({"tiger.core.service.*"})
public class BizConfig {
}
