import circularependency.ClazzA;
import circularependency.ClazzB;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
    ref:https://juejin.cn/post/6958962738609520676
*/
public class Application {

    private static Map<String,Object> singletonObjects = new ConcurrentHashMap<String,Object>();

    public static void main(String[] args) throws Exception {
        //这样会产生 StackOverflowError，这里产生了循环依赖
        //new ClazzA();

        System.out.println(getBean(ClazzB.class).getA());
        System.out.println(getBean(ClazzA.class).getB());

    }


    private static<T> T getBean(Class<T> beanClass) throws Exception{

        String beanName = beanClass.getSimpleName().toLowerCase();
        if(singletonObjects.containsKey(beanName)){
            return (T)singletonObjects.get(beanName);
        }

        T t = beanClass.newInstance();
        singletonObjects.put(beanName,t);

        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields) {
            field.setAccessible(true);
            Class<?> fieldClass = field.getType();

            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            field.set(t,singletonObjects.containsKey(fieldBeanName)
                    ? singletonObjects.get(fieldBeanName) : getBean(fieldClass));
            field.setAccessible(false);
        }
        return t;
    }
}
