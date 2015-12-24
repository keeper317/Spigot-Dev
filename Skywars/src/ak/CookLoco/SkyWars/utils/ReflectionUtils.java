package ak.CookLoco.SkyWars.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

public final class ReflectionUtils
{
  public static Constructor<?> getConstructor(Class<?> paramClass, Class<?>... paramVarArgs)
  {
    Class[] arrayOfClass = DataType.getPrimitive(paramVarArgs);
    Constructor[] arrayOfConstructor;
    int j = (arrayOfConstructor = paramClass.getConstructors()).length;
    for (int i = 0; i < j; i++)
    {
      Constructor localConstructor = arrayOfConstructor[i];
      if (DataType.compare(DataType.getPrimitive(localConstructor.getParameterTypes()), arrayOfClass)) {
        return localConstructor;
      }
    }
    throw new NoSuchMethodException("There is no such constructor in this class with the specified parameter types");
  }
  
  public static Constructor<?> getConstructor(String paramString, PackageType paramPackageType, Class<?>... paramVarArgs)
  {
    return getConstructor(paramPackageType.getClass(paramString), paramVarArgs);
  }
  
  public static Object instantiateObject(Class<?> paramClass, Object... paramVarArgs)
  {
    return getConstructor(paramClass, DataType.getPrimitive(paramVarArgs)).newInstance(paramVarArgs);
  }
  
  public static Object instantiateObject(String paramString, PackageType paramPackageType, Object... paramVarArgs)
  {
    return instantiateObject(paramPackageType.getClass(paramString), paramVarArgs);
  }
  
  public static Method getMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs)
  {
    Class[] arrayOfClass = DataType.getPrimitive(paramVarArgs);
    Method[] arrayOfMethod;
    int j = (arrayOfMethod = paramClass.getMethods()).length;
    for (int i = 0; i < j; i++)
    {
      Method localMethod = arrayOfMethod[i];
      if ((localMethod.getName().equals(paramString)) && (DataType.compare(DataType.getPrimitive(localMethod.getParameterTypes()), arrayOfClass))) {
        return localMethod;
      }
    }
    throw new NoSuchMethodException("There is no such method in this class with the specified name and parameter types");
  }
  
  public static Method getMethod(String paramString1, PackageType paramPackageType, String paramString2, Class<?>... paramVarArgs)
  {
    return getMethod(paramPackageType.getClass(paramString1), paramString2, paramVarArgs);
  }
  
  public static Object invokeMethod(Object paramObject, String paramString, Object... paramVarArgs)
  {
    return getMethod(paramObject.getClass(), paramString, DataType.getPrimitive(paramVarArgs)).invoke(paramObject, paramVarArgs);
  }
  
  public static Object invokeMethod(Object paramObject, Class<?> paramClass, String paramString, Object... paramVarArgs)
  {
    return getMethod(paramClass, paramString, DataType.getPrimitive(paramVarArgs)).invoke(paramObject, paramVarArgs);
  }
  
  public static Object invokeMethod(Object paramObject, String paramString1, PackageType paramPackageType, String paramString2, Object... paramVarArgs)
  {
    return invokeMethod(paramObject, paramPackageType.getClass(paramString1), paramString2, paramVarArgs);
  }
  
  public static Field getField(Class<?> paramClass, boolean paramBoolean, String paramString)
  {
    Field localField = paramBoolean ? paramClass.getDeclaredField(paramString) : paramClass.getField(paramString);
    localField.setAccessible(true);
    return localField;
  }
  
  public static Field getField(String paramString1, PackageType paramPackageType, boolean paramBoolean, String paramString2)
  {
    return getField(paramPackageType.getClass(paramString1), paramBoolean, paramString2);
  }
  
  public static Object getValue(Object paramObject, Class<?> paramClass, boolean paramBoolean, String paramString)
  {
    return getField(paramClass, paramBoolean, paramString).get(paramObject);
  }
  
  public static Object getValue(Object paramObject, String paramString1, PackageType paramPackageType, boolean paramBoolean, String paramString2)
  {
    return getValue(paramObject, paramPackageType.getClass(paramString1), paramBoolean, paramString2);
  }
  
  public static Object getValue(Object paramObject, boolean paramBoolean, String paramString)
  {
    return getValue(paramObject, paramObject.getClass(), paramBoolean, paramString);
  }
  
  public static void setValue(Object paramObject1, Class<?> paramClass, boolean paramBoolean, String paramString, Object paramObject2)
  {
    getField(paramClass, paramBoolean, paramString).set(paramObject1, paramObject2);
  }
  
  public static void setValue(Object paramObject1, String paramString1, PackageType paramPackageType, boolean paramBoolean, String paramString2, Object paramObject2)
  {
    setValue(paramObject1, paramPackageType.getClass(paramString1), paramBoolean, paramString2, paramObject2);
  }
  
  public static void setValue(Object paramObject1, boolean paramBoolean, String paramString, Object paramObject2)
  {
    setValue(paramObject1, paramObject1.getClass(), paramBoolean, paramString, paramObject2);
  }
  
  public static enum PackageType
  {
    MINECRAFT_SERVER("net.minecraft.server." + getServerVersion()),  CRAFTBUKKIT("org.bukkit.craftbukkit." + getServerVersion()),  CRAFTBUKKIT_BLOCK(CRAFTBUKKIT, "block"),  CRAFTBUKKIT_CHUNKIO(CRAFTBUKKIT, "chunkio"),  CRAFTBUKKIT_COMMAND(CRAFTBUKKIT, "command"),  CRAFTBUKKIT_CONVERSATIONS(CRAFTBUKKIT, "conversations"),  CRAFTBUKKIT_ENCHANTMENS(CRAFTBUKKIT, "enchantments"),  CRAFTBUKKIT_ENTITY(CRAFTBUKKIT, "entity"),  CRAFTBUKKIT_EVENT(CRAFTBUKKIT, "event"),  CRAFTBUKKIT_GENERATOR(CRAFTBUKKIT, "generator"),  CRAFTBUKKIT_HELP(CRAFTBUKKIT, "help"),  CRAFTBUKKIT_INVENTORY(CRAFTBUKKIT, "inventory"),  CRAFTBUKKIT_MAP(CRAFTBUKKIT, "map"),  CRAFTBUKKIT_METADATA(CRAFTBUKKIT, "metadata"),  CRAFTBUKKIT_POTION(CRAFTBUKKIT, "potion"),  CRAFTBUKKIT_PROJECTILES(CRAFTBUKKIT, "projectiles"),  CRAFTBUKKIT_SCHEDULER(CRAFTBUKKIT, "scheduler"),  CRAFTBUKKIT_SCOREBOARD(CRAFTBUKKIT, "scoreboard"),  CRAFTBUKKIT_UPDATER(CRAFTBUKKIT, "updater"),  CRAFTBUKKIT_UTIL(CRAFTBUKKIT, "util");
    
    private final String path;
    
    private PackageType(String paramString1)
    {
      this.path = paramString1;
    }
    
    private PackageType(PackageType paramPackageType, String paramString1)
    {
      this(paramPackageType + "." + paramString1);
    }
    
    public String getPath()
    {
      return this.path;
    }
    
    public Class<?> getClass(String paramString)
    {
      return Class.forName(this + "." + paramString);
    }
    
    public String toString()
    {
      return this.path;
    }
    
    public static String getServerVersion()
    {
      return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }
  }
  
  public static enum DataType
  {
    BYTE(Byte.TYPE, Byte.class),  SHORT(Short.TYPE, Short.class),  INTEGER(Integer.TYPE, Integer.class),  LONG(Long.TYPE, Long.class),  CHARACTER(Character.TYPE, Character.class),  FLOAT(Float.TYPE, Float.class),  DOUBLE(Double.TYPE, Double.class),  BOOLEAN(Boolean.TYPE, Boolean.class);
    
    private static final Map<Class<?>, DataType> CLASS_MAP;
    private final Class<?> primitive;
    private final Class<?> reference;
    
    static
    {
      CLASS_MAP = new HashMap();
      DataType[] arrayOfDataType;
      int j = (arrayOfDataType = values()).length;
      for (int i = 0; i < j; i++)
      {
        DataType localDataType = arrayOfDataType[i];
        CLASS_MAP.put(localDataType.primitive, localDataType);
        CLASS_MAP.put(localDataType.reference, localDataType);
      }
    }
    
    private DataType(Class<?> paramClass1, Class<?> paramClass2)
    {
      this.primitive = paramClass1;
      this.reference = paramClass2;
    }
    
    public Class<?> getPrimitive()
    {
      return this.primitive;
    }
    
    public Class<?> getReference()
    {
      return this.reference;
    }
    
    public static DataType fromClass(Class<?> paramClass)
    {
      return (DataType)CLASS_MAP.get(paramClass);
    }
    
    public static Class<?> getPrimitive(Class<?> paramClass)
    {
      DataType localDataType = fromClass(paramClass);
      return localDataType == null ? paramClass : localDataType.getPrimitive();
    }
    
    public static Class<?> getReference(Class<?> paramClass)
    {
      DataType localDataType = fromClass(paramClass);
      return localDataType == null ? paramClass : localDataType.getReference();
    }
    
    public static Class<?>[] getPrimitive(Class<?>[] paramArrayOfClass)
    {
      int i = paramArrayOfClass == null ? 0 : paramArrayOfClass.length;
      Class[] arrayOfClass = new Class[i];
      for (int j = 0; j < i; j++) {
        arrayOfClass[j] = getPrimitive(paramArrayOfClass[j]);
      }
      return arrayOfClass;
    }
    
    public static Class<?>[] getReference(Class<?>[] paramArrayOfClass)
    {
      int i = paramArrayOfClass == null ? 0 : paramArrayOfClass.length;
      Class[] arrayOfClass = new Class[i];
      for (int j = 0; j < i; j++) {
        arrayOfClass[j] = getReference(paramArrayOfClass[j]);
      }
      return arrayOfClass;
    }
    
    public static Class<?>[] getPrimitive(Object[] paramArrayOfObject)
    {
      int i = paramArrayOfObject == null ? 0 : paramArrayOfObject.length;
      Class[] arrayOfClass = new Class[i];
      for (int j = 0; j < i; j++) {
        arrayOfClass[j] = getPrimitive(paramArrayOfObject[j].getClass());
      }
      return arrayOfClass;
    }
    
    public static Class<?>[] getReference(Object[] paramArrayOfObject)
    {
      int i = paramArrayOfObject == null ? 0 : paramArrayOfObject.length;
      Class[] arrayOfClass = new Class[i];
      for (int j = 0; j < i; j++) {
        arrayOfClass[j] = getReference(paramArrayOfObject[j].getClass());
      }
      return arrayOfClass;
    }
    
    public static boolean compare(Class<?>[] paramArrayOfClass1, Class<?>[] paramArrayOfClass2)
    {
      if ((paramArrayOfClass1 == null) || (paramArrayOfClass2 == null) || (paramArrayOfClass1.length != paramArrayOfClass2.length)) {
        return false;
      }
      for (int i = 0; i < paramArrayOfClass1.length; i++)
      {
        Class<?> localClass1 = paramArrayOfClass1[i];
        Class<?> localClass2 = paramArrayOfClass2[i];
        if ((!localClass1.equals(localClass2)) && (!localClass1.isAssignableFrom(localClass2))) {
          return false;
        }
      }
      return true;
    }
  }
}


/* Location:              C:\Users\Cody\AppData\Roaming\Skype\My Skype Received Files\SkyWars.jar!\ak\CookLoco\SkyWars\utils\ReflectionUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */