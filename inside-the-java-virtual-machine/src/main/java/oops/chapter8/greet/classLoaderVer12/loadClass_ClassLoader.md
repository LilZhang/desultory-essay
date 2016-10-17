# ClassLoader 中的 loadClass() 方法解析

- Class<?> loadClass(String name, boolean resolve)

## 先写总结
1. 检查所要装载的类是否已被装载，若已装载则使用其 Class 对象
2.1 若无，让自己的父装载器装载该类(父装载器的loadClass()方法)
2.2 若无父类装载器，则让启动类装载器(BOOTSRAP)装载该类
3. 还没有，则执行用户可覆盖的 findClass() 方法
4. 若 resolve 为true，则调用 resolveClass() 连接这个类

部分源码如下
```
// 该 ClassLoader 的父类装载器
private final ClassLoader parent;


protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
	// 方法区的类数据操作时需同步
        synchronized (getClassLoadingLock(name)) {
            
	    // 所要装载的类是否已被装载
	    // native
            Class c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {

                    if (parent != null) {

		    	// 若没有被装载
		    	// 且有父装载器
		    	// 交由父装载器装载所要装载的类
                        c = parent.loadClass(name, false);
                    } else {

			// 没有父类装载器(其实不)
			// 交由BOOTSTRAP(启动类装载器)装载所要装载的类
			// 其实，parent 为 null 并不表示没有父装载器
			// 只是将父装载器设置为 BOOTSRAP	
			// native
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    long t1 = System.nanoTime();

		    // 如果还没装载出来
		    // 找自定义的 findClass() 方法
	  	    // 在 java.lang.ClassLoader 中该方法直接抛出 ClassNotFoundException
		    // 所以需要使用者在自己定义的 ClassLoader 子类中 override 该方法
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```
