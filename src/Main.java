/**
 * 不存在运行期的对象创建循环, 不存在循环依赖
 * 会导致栈溢出/无限递归的“循环依赖”，是 A 的构造函数需要 new B，B 的构造函数又需要 new A
 * LoginState、UnLoginState 的构造函数什么都没做，不创建 AppContext
 * UserState 只是声明了一个 appContext 字段（UserState.java:10），由外部通过 setAppContext 注入，而不是自己 new 出来
 *
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.collect();
        context.comment("说的太好了，双手双脚给个赞👍");
        context.forward();
    }
}