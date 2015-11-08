/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.web.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * 项目启动类
 *
 * @author yiliang.gyl
 * @version $ID: v 0.1 4:27 PM yiliang.gyl Exp $
 */
@SpringBootApplication
public class TigerWebMain extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplication(TigerWebMain.class).run(args);
    }
}
