# 状态模式
状态机demo 简单java工程

1. 编译期的相互引用（类依赖循环）—— Java 允许
AppContext 引用了 UserState（第 9、11、13 行），而 UserState/UnLoginState 又反过来引用 AppContext（如 UserState.java:10、UnLoginState.java:29）。这在 Java 里完全合法，因为 Java 编译器是多趟扫描的，类之间可以互相引用，不存在“先有鸡还是先有蛋”的编译障碍。

2. 运行期的对象创建循环——这里根本不存在
真正会导致栈溢出/无限递归的“循环依赖”，是 A 的构造函数需要 new B，B 的构造函数又需要 new A。但本代码不是这样：

LoginState、UnLoginState 的构造函数什么都没做，不创建 AppContext（LoginState.java 第 7-23 行、UnLoginState.java 第 7-31 行）。
UserState 只是声明了一个 appContext 字段（UserState.java:10），由外部通过 setAppContext 注入，而不是自己 new 出来。
所以 new AppContext() 的创建顺序是：

AppContext.javaL9-L18
应用
    public static final UserState LOGIN_STATE = new LoginState();
    public static final UserState UNLOGIN_STATE = new UnLoginState();
    private UserState currentState = UNLOGIN_STATE;
    {
        UNLOGIN_STATE.setAppContext(this);
        LOGIN_STATE.setAppContext(this);
    }
先创建两个无依赖的 State 对象（new LoginState()/new UnLoginState()）；
再在实例初始化块里把 this（AppContext）通过 setAppContext 传给已存在的 State。
整个过程没有“创建 A→创建 B→又创建 A”的递归，因此不会无限循环。

3. 只是“对象引用环”，不影响 GC
最终形成的对象图是：AppContext → currentState → appContext → AppContext，这是一个引用环。但 JVM 垃圾回收用的是可达性分析，不是引用计数，所以这种互相引用不会造成内存泄漏，也不算需要解决的“循环依赖”问题。

总结
不产生循环依赖的核心原因是：UserState 不负责创建 AppContext，它只持有外部注入的引用。打破了“构造时互相 new”这条会导致无限递归的链路。这是状态模式（State Pattern）的典型写法——Context 持有当前 State，State 反向持有 Context 以便切换状态（UnLoginState.java:29），靠的是引用注入而非构造依赖。
