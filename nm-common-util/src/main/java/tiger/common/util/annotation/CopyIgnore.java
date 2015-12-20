/**
 * 404 Studio Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package tiger.common.util.annotation;

import java.lang.annotation.*;

/**
 *
 * @author yiliang.gyl
 * @version v 0.1 Sep 23, 2015 11:29:56 AM yiliang.gyl Exp $
 */
@Documented
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyIgnore {

}
