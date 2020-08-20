# SPI机制

+ 第1步： 初始化


    // 加载接口类型  
    public static <S> ServiceLoader<S> load(Class<S> service) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        return ServiceLoader.load(service, cl);
    }
        // 重载load方法
        public static <S> ServiceLoader<S> load(Class<S> service,
                                                ClassLoader loader)
        {
            return new ServiceLoader<>(service, loader);
        }
        
            // 实例化ServiceLoader
            private ServiceLoader(Class<S> svc, ClassLoader cl) {
                service = Objects.requireNonNull(svc, "Service interface cannot be null");
                // 获取类加载器
                loader = (cl == null) ? ClassLoader.getSystemClassLoader() : cl;
                // 获取访问控制器
                acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;
                // 重加载
                reload();
            }
            
                public void reload() {
                    // 清除缓存
                    providers.clear();
                    // 初始化懒迭代器
                    lookupIterator = new LazyIterator(service, loader);
                }

+ 第2步： 判断内容

    
       public Iterator<S> iterator() {
            return new Iterator<S>() {
    
                Iterator<Map.Entry<String,S>> knownProviders
                    = providers.entrySet().iterator();
    
                public boolean hasNext() {
                    // 缓存中是否存在
                    if (knownProviders.hasNext())
                        return true;
                    // 返回懒加载器hasNext()
                    return lookupIterator.hasNext();
                }
    
                public S next() {
                    if (knownProviders.hasNext())
                        return knownProviders.next().getValue();
                    return lookupIterator.next();
                }
    
                public void remove() {
                    throw new UnsupportedOperationException();
                }
    
            };
        }
        
        public boolean hasNext() {
            if (acc == null) {
                // 进入懒加载器hasNextService方法
                return hasNextService();
            } else {
                PrivilegedAction<Boolean> action = new PrivilegedAction<Boolean>() {
                    public Boolean run() { return hasNextService(); }
                };
                return AccessController.doPrivileged(action, acc);
            }
        }
                        
        private boolean hasNextService() {
            if (nextName != null) {
                return true;
            }
            if (configs == null) {
                try {
                    // 文件全路径名
                    String fullName = PREFIX + service.getName();
                    // 类加载器判断
                    if (loader == null)
                        configs = ClassLoader.getSystemResources(fullName);
                    else
                        configs = loader.getResources(fullName);
                } catch (IOException x) {
                    fail(service, "Error locating configuration files", x);
                }
            }
            while ((pending == null) || !pending.hasNext()) {
                if (!configs.hasMoreElements()) {
                    return false;
                }
                // 读取文件内容迭代器
                pending = parse(service, configs.nextElement());
            }
            nextName = pending.next();
            return true;
        }
        
+ 第3步：实例化


        private S nextService() {
            if (!hasNextService())
                throw new NoSuchElementException();
            String cn = nextName;
            nextName = null;
            Class<?> c = null;
            try {
                c = Class.forName(cn, false, loader);
            } catch (ClassNotFoundException x) {
                fail(service,
                     "Provider " + cn + " not found");
            }
            if (!service.isAssignableFrom(c)) {
                fail(service,
                     "Provider " + cn  + " not a subtype");
            }
            try {
                S p = service.cast(c.newInstance());
                providers.put(cn, p);
                return p;
            } catch (Throwable x) {
                fail(service,
                     "Provider " + cn + " could not be instantiated",
                     x);
            }
            throw new Error();          // This cannot happen
        }

### 优点
+ Java SPI机制实现了解耦
+ 延迟加载

### 缺点
+ 需要实现接口的所有实现类，全部加载且实例化，浪费
+ 多线程并发ServiceLoader类不安全
