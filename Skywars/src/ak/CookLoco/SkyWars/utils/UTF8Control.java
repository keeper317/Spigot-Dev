package ak.CookLoco.SkyWars.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivilegedExceptionAction;
import java.util.ResourceBundle.Control;

public class UTF8Control
  extends ResourceBundle.Control
{
  /* Error */
  public java.util.ResourceBundle newBundle(String paramString1, java.util.Locale paramLocale, String paramString2, ClassLoader paramClassLoader, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokevirtual 24	ak/CookLoco/SkyWars/utils/UTF8Control:toBundleName	(Ljava/lang/String;Ljava/util/Locale;)Ljava/lang/String;
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 7
    //   11: aload_3
    //   12: ldc 26
    //   14: invokevirtual 32	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   17: ifeq +71 -> 88
    //   20: aload 4
    //   22: aload 6
    //   24: invokevirtual 38	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   27: astore 8
    //   29: ldc 9
    //   31: aload 8
    //   33: invokevirtual 44	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   36: ifeq +16 -> 52
    //   39: aload 8
    //   41: invokevirtual 48	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   44: checkcast 9	java/util/ResourceBundle
    //   47: astore 7
    //   49: goto +175 -> 224
    //   52: new 50	java/lang/ClassCastException
    //   55: dup
    //   56: new 52	java/lang/StringBuilder
    //   59: dup
    //   60: aload 8
    //   62: invokevirtual 56	java/lang/Class:getName	()Ljava/lang/String;
    //   65: invokestatic 60	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   68: invokespecial 63	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   71: ldc 65
    //   73: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokespecial 73	java/lang/ClassCastException:<init>	(Ljava/lang/String;)V
    //   82: athrow
    //   83: astore 8
    //   85: goto +139 -> 224
    //   88: aload_3
    //   89: ldc 77
    //   91: invokevirtual 32	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   94: ifeq +106 -> 200
    //   97: aload_0
    //   98: aload 6
    //   100: ldc 79
    //   102: invokevirtual 83	ak/CookLoco/SkyWars/utils/UTF8Control:toResourceName	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   105: astore 8
    //   107: aload 4
    //   109: astore 9
    //   111: iload 5
    //   113: istore 10
    //   115: aconst_null
    //   116: astore 11
    //   118: new 7	ak/CookLoco/SkyWars/utils/UTF8Control$1
    //   121: dup
    //   122: aload_0
    //   123: iload 10
    //   125: aload 9
    //   127: aload 8
    //   129: invokespecial 86	ak/CookLoco/SkyWars/utils/UTF8Control$1:<init>	(Lak/CookLoco/SkyWars/utils/UTF8Control;ZLjava/lang/ClassLoader;Ljava/lang/String;)V
    //   132: invokestatic 92	java/security/AccessController:doPrivileged	(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;
    //   135: checkcast 94	java/io/InputStream
    //   138: astore 11
    //   140: goto +14 -> 154
    //   143: astore 12
    //   145: aload 12
    //   147: invokevirtual 98	java/security/PrivilegedActionException:getException	()Ljava/lang/Exception;
    //   150: checkcast 100	java/io/IOException
    //   153: athrow
    //   154: aload 11
    //   156: ifnull +68 -> 224
    //   159: new 102	java/util/PropertyResourceBundle
    //   162: dup
    //   163: new 104	java/io/InputStreamReader
    //   166: dup
    //   167: aload 11
    //   169: ldc 106
    //   171: invokespecial 109	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   174: invokespecial 112	java/util/PropertyResourceBundle:<init>	(Ljava/io/Reader;)V
    //   177: astore 7
    //   179: goto +13 -> 192
    //   182: astore 12
    //   184: aload 11
    //   186: invokevirtual 117	java/io/InputStream:close	()V
    //   189: aload 12
    //   191: athrow
    //   192: aload 11
    //   194: invokevirtual 117	java/io/InputStream:close	()V
    //   197: goto +27 -> 224
    //   200: new 119	java/lang/IllegalArgumentException
    //   203: dup
    //   204: new 52	java/lang/StringBuilder
    //   207: dup
    //   208: ldc 121
    //   210: invokespecial 63	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   213: aload_3
    //   214: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokespecial 122	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   223: athrow
    //   224: aload 7
    //   226: areturn
    // Line number table:
    //   Java source line #19	-> byte code offset #0
    //   Java source line #20	-> byte code offset #8
    //   Java source line #21	-> byte code offset #11
    //   Java source line #24	-> byte code offset #20
    //   Java source line #28	-> byte code offset #29
    //   Java source line #29	-> byte code offset #39
    //   Java source line #30	-> byte code offset #49
    //   Java source line #31	-> byte code offset #52
    //   Java source line #33	-> byte code offset #83
    //   Java source line #35	-> byte code offset #85
    //   Java source line #36	-> byte code offset #97
    //   Java source line #37	-> byte code offset #107
    //   Java source line #38	-> byte code offset #111
    //   Java source line #39	-> byte code offset #115
    //   Java source line #41	-> byte code offset #118
    //   Java source line #61	-> byte code offset #140
    //   Java source line #62	-> byte code offset #145
    //   Java source line #64	-> byte code offset #154
    //   Java source line #67	-> byte code offset #159
    //   Java source line #68	-> byte code offset #179
    //   Java source line #69	-> byte code offset #184
    //   Java source line #70	-> byte code offset #189
    //   Java source line #69	-> byte code offset #192
    //   Java source line #72	-> byte code offset #197
    //   Java source line #73	-> byte code offset #200
    //   Java source line #75	-> byte code offset #224
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	this	UTF8Control
    //   0	227	1	paramString1	String
    //   0	227	2	paramLocale	java.util.Locale
    //   0	227	3	paramString2	String
    //   0	227	4	paramClassLoader	ClassLoader
    //   0	227	5	paramBoolean	boolean
    //   6	93	6	str1	String
    //   9	216	7	localObject1	Object
    //   27	34	8	localClass	Class
    //   83	1	8	localClassNotFoundException	ClassNotFoundException
    //   105	23	8	str2	String
    //   109	17	9	localClassLoader	ClassLoader
    //   113	11	10	bool	boolean
    //   116	77	11	localInputStream	InputStream
    //   143	3	12	localPrivilegedActionException	java.security.PrivilegedActionException
    //   182	8	12	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   20	83	83	java/lang/ClassNotFoundException
    //   118	140	143	java/security/PrivilegedActionException
    //   159	182	182	finally
  }
}