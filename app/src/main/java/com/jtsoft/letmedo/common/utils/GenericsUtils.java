package com.jtsoft.letmedo.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型工具类
 */
public class GenericsUtils {


    public static boolean isListSuperType(Class clazz) {
        Class newClazz = GenericsUtils.getSuperClassGenricType(clazz);
        if (List.class.isAssignableFrom(newClazz)) {
            return true;
        }
        return false;
    }

    public static boolean isListType(Class clazz) {
        if (List.class.isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }

    public static <T> T newObject(Class clazz, Class<T> baseClass){
        Class newClazz = GenericsUtils.getSuperClassGenricType(clazz);
        if(baseClass.isAssignableFrom(newClazz)){
            try {
                return (T)newClazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
    /**
     * 通过反射,获得指定类的父类的泛型参数的实际类型. 如DaoSupport<Buyer>
     *
     * @param clazz
     *            clazz 需要反射的类,该类必须继承范型父类
     * @param index
     *            泛型参数所在索引,从0开始.
     * @return 范型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz, int index) {

        Type genType = clazz.getGenericSuperclass();// 得到泛型父类

        // 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
        if (!(genType instanceof ParameterizedType)) {

            return Object.class;
        }

        // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
        // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {

            throw new RuntimeException("你输入的索引"
                    + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        if (!(params[index] instanceof Class)) {

            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 通过反射,获得指定类的父类的第一个泛型参数的实际类型. 如DaoSupport<Buyer>
     *
     * @param clazz
     *            clazz 需要反射的类,该类必须继承泛型父类
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(Class clazz) {

        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得方法返回值泛型参数的实际类型. 如: public Map<String, Buyer> getNames(){}
     *
     * @param method
     *            method 方法
     * @param index index 泛型参数所在索引,从0开始.
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static Class getMethodGenericReturnType(Method method, int index) {

        Type returnType = method.getGenericReturnType();

        if (returnType instanceof ParameterizedType) {

            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();

            if (index >= typeArguments.length || index < 0) {

                throw new RuntimeException("你输入的索引"
                        + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            return (Class) typeArguments[index];
        }
        return Object.class;
    }

    /**
     * 通过反射,获得方法返回值第一个泛型参数的实际类型. 如: public Map<String, Buyer> getNames(){}
     *
     * @param method 方法
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static Class getMethodGenericReturnType(Method method) {

        return getMethodGenericReturnType(method, 0);
    }

    /**
     * 通过反射,获得方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String,
     * Buyer> maps, List<String> names){}
     *
     * @param method 方法
     * @param index 第几个输入参数
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    @SuppressWarnings("unchecked")
    public static List<Class> getMethodGenericParameterTypes(Method method,
                                                             int index) {

        List<Class> results = new ArrayList<Class>();
        Type[] genericParameterTypes = method.getGenericParameterTypes();

        if (index >= genericParameterTypes.length || index < 0) {

            throw new RuntimeException("你输入的索引"
                    + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        Type genericParameterType = genericParameterTypes[index];

        if (genericParameterType instanceof ParameterizedType) {

            ParameterizedType aType = (ParameterizedType) genericParameterType;
            Type[] parameterArgTypes = aType.getActualTypeArguments();
            for (Type parameterArgType : parameterArgTypes) {
                Class parameterArgClass = (Class) parameterArgType;
                results.add(parameterArgClass);
            }
            return results;
        }
        return results;
    }

    /**
     * 通过反射,获得方法输入参数第一个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String, Buyer>
     * maps, List<String> names){}
     *
     * @param method 方法
     * @return 输入参数的泛型参数的实际类型集合, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回空集合
     */
    @SuppressWarnings("unchecked")
    public static List<Class> getMethodGenericParameterTypes(Method method) {

        return getMethodGenericParameterTypes(method, 0);
    }

    /**
     * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
     *
     * @param field 字段
     * @param index 泛型参数所在索引,从0开始.
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static Class getFieldGenericType(Field field, int index) {

        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {

            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            if (index >= fieldArgTypes.length || index < 0) {

                throw new RuntimeException("你输入的索引"
                        + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            return (Class) fieldArgTypes[index];
        }
        return Object.class;
    }

    /**
     * 通过反射,获得Field泛型参数的实际类型. 如: public Map<String, Buyer> names;
     *
     * @param field 字段
     * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回
     *         <code>Object.class</code>
     */
    @SuppressWarnings("unchecked")
    public static Class getFieldGenericType(Field field) {

        return getFieldGenericType(field, 0);
    }

    /**
     * 根据属性名获取属性值
     * */
    private static Object getFieldValueByName2(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
            return null;
        }
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            // 设置访问权限（这点对于有过android开发经验的可以说很熟悉）
            field.setAccessible(true);
            // 得到私有的变量值
            Object value = field.get(o);
            return value;
        } catch (Exception e) {
            //log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 获取属性名数组
     * */
    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        if(fields!=null&&fields.length>0) {
            String[] fieldNames = new String[fields.length];
            if (fieldNames != null&&fieldNames.length>0) {
                for (int i = 0; i < fields.length; i++) {
                    System.out.println(fields[i].getType());
                    fieldNames[i] = fields[i].getName();
                }
                return fieldNames;
            }
        }
        return  new String[0];
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * */
    private static List getFiledsInfo(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        List list = new ArrayList();
        Map infoMap=null;
        for(int i=0;i<fields.length;i++){
            infoMap = new HashMap();
            infoMap.put("type", fields[i].getType().toString());
            infoMap.put("name", fields[i].getName());
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */

    public static Object[] getFiledValues(Object o){
        String[] fieldNames=getFiledName(o);
        Object[] value=new Object[fieldNames.length];
        for(int i=0;i<fieldNames.length;i++){
            value[i]=getFieldValueByName(fieldNames[i], o);

        }
        return value;
    }

    public static List getListFiledValue(Object o){
        String[] fieldNames=getFiledName(o);
        if(fieldNames!=null&&fieldNames.length>0) {
            Object[] value = new Object[fieldNames.length];
            if(value!=null&&value.length>0) {
                for (int i = 0; i < fieldNames.length; i++) {
                    value[i] = getFieldValueByName(fieldNames[i], o);
                    if (value[i]!=null&&isListType(value[i].getClass())) {
                        return (List) value[i];
                    }
                }
            }
        }
        return null;
    }
}