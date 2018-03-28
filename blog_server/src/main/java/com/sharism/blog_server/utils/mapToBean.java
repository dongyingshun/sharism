package com.sharism.blog_server.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
   * @ProjectName:    mapToBean
   * @Description:    Map对象转为指定的实体类对象
   * @Author:         DYS
   * @CreateDate:     2018/3/18-19:14
   * @UpdateUser:     DYS
   * @UpdateDate:
   * @UpdateRemark:   The modified content
   * @Version:        1.0
   */
public class mapToBean {
    public static Object mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
           Object obj = clazz.newInstance();
           if(map != null && map.size() > 0) {
               for(Map.Entry<String, Object> entry : map.entrySet()) {
                   String propertyName = entry.getKey();       //属性名
                   Object value = entry.getValue();
                   String setMethodName = "set"
                           + propertyName.substring(0, 1).toUpperCase()
                           + propertyName.substring(1);
                   Field field = getClassField(clazz, propertyName);
                   if(field==null)
                       continue;
                   Class<?> fieldTypeClass = field.getType();
                   value = convertValType(value, fieldTypeClass);
                   try{
                       clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                   }catch(NoSuchMethodException e){
                       e.printStackTrace();
                   }
               }
           }
           return obj;
       }
    /**
        * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
        *
        * @param clazz 指定的class
        * @param fieldName 字段名称
        * @return Field对象
        */
       private static Field getClassField(Class<?> clazz, String fieldName) {
           if( Object.class.getName().equals(clazz.getName())) {
               return null;
           }
           Field []declaredFields = clazz.getDeclaredFields();
           for (Field field : declaredFields) {
               if (field.getName().equals(fieldName)) {
                   return field;
               }
           }

           Class<?> superClass = clazz.getSuperclass();
           if(superClass != null) {// 简单的递归一下
               return getClassField(superClass, fieldName);
           }
           return null;
       }

        /**
        * 将Object类型的值，转换成bean对象属性里对应的类型值
        *
        * @param value Object对象值
        * @param fieldTypeClass 属性的类型
        * @return 转换后的值
        */
       private static Object convertValType(Object value, Class<?> fieldTypeClass) {
           Object retVal = null;
           if(Long.class.getName().equals(fieldTypeClass.getName())
                   || long.class.getName().equals(fieldTypeClass.getName())) {
               retVal = Long.parseLong(value.toString());
           } else if(Integer.class.getName().equals(fieldTypeClass.getName())
                   || int.class.getName().equals(fieldTypeClass.getName())) {
               retVal = Integer.parseInt(value.toString());
           } else if(Float.class.getName().equals(fieldTypeClass.getName())
                   || float.class.getName().equals(fieldTypeClass.getName())) {
               retVal = Float.parseFloat(value.toString());
           } else if(Double.class.getName().equals(fieldTypeClass.getName())
                   || double.class.getName().equals(fieldTypeClass.getName())) {
               retVal = Double.parseDouble(value.toString());
           } else {
               retVal = value;
           }
           return retVal;
       }

}
