package tiger.common.util.annotation;

import tiger.common.util.permission.PermissionRelation;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /** 权限值 */
    String[]value() default {};

    /** 权限的关系 */
    PermissionRelation relation() default PermissionRelation.OR;
}