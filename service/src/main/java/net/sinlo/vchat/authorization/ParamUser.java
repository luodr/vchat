package net.sinlo.vchat.authorization;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})//Annotation所修饰的对象范围:方法参数
@Retention(RetentionPolicy.RUNTIME)//Annotation被保留时间:运行时保留(有效)
@Documented//标记注解:java工具文档化
public @interface ParamUser {
}
