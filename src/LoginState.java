/**
 * @author Jari
 * @version 1.0
 * @description: TODO
 * @date 2026/7/29 17:15
 */
public class LoginState extends UserState {

    @Override
    public void forward() {
        System.out.println("转发成功！");
    }

    @Override
    public void collect() {
        System.out.println("收藏成功！");
    }

    @Override
    public void comment(String comment) {
        System.out.println("评论成功,内容是：" + comment);
    }
}
