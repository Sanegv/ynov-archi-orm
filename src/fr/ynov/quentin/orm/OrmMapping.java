import java.lang.reflect.Constructor;

public class OrmMapping {
    public <T> T getInstanceOf(Class<T> aClass, Object... params) throws Exception {
        Constructor constructors[] = aClass.getConstructors();
        for(Constructor constructor : constructors){
            try {
                constructor.newInstance(params);
            } catch (Exception e) {
            }
        }

        return null;
    }
}
