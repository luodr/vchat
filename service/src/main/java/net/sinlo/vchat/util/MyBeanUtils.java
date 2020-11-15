package net.sinlo.vchat.util;

import org.springframework.beans.BeanUtils;

public class MyBeanUtils  {
  public static<T> T  copyProperties(Object source,Class toClass){
        try{
            T target=(T)toClass.newInstance();
            BeanUtils.copyProperties(source,target);
            return  target;
        }catch (Exception e){
            System.err.println("Bean转换错误!");
        }
         return  null;
    }
}
