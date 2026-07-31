/**
 * @author Jari
 * @version 1.0
 * @description: TODO
 * @date 2026/7/29 17:16
 */
public class UnLoginState extends UserState {

    @Override
    public void forward() {
        forward2Login();
        this.appContext.forward();
    }

    @Override
    public void collect() {
        forward2Login();
        this.appContext.collect();
    }

    @Override
    public void comment(String comment) {
        forward2Login();
        this.appContext.comment(comment);
    }

    private void forward2Login() {
        System.out.println("跳转到登录页面！");
        this.appContext.setState(this.appContext.LOGIN_STATE);
    }
}
