####第 1 条 考虑用静态工厂方法替代构造器

静态工厂方法对比构造器优势
    1.静态工厂方法更加易于阅读
     new BigInteger(int, int, Random)
     BigInteger.probablePrime() 
     显然静态工厂方法更为清楚
     如果一个类需要许多带有相同签名的构造器,可用静态工厂方法替代构造器
     
    2.静态工厂方法不必每次调用它们时候都创建一个新的对象
    
    3.静态工厂方法可以返回原返回类型的任何子类型的对象
    
    4.静态工厂方法创建参数化实例的时候,在创建参数化类型实例的时候,他们使代码变得更加简洁
    
    Map<String,List<String>> m = new HashMap<String,List<String>>
    如果jdk提供了静态工厂方法
    Map<String,List<String>> m = HashMap.newInstance();
    
    public static<K, V> HashMap<K, V> new Instance(){
        return new HashMap<K, V>();
    }
    
    ``
静态工厂方法的缺陷:
    1.类如果不含公有的或者私有的或者受保护的构造器,就不能被实例化
    2.它们与其他静态方法实际上没有任何区别  