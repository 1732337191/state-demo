/**
 * @author Jari
 * @version 1.0
 * @description: TODO
 * @date 2026/7/29 17:11
 */
public abstract class UserState {


    public AppContext appContext;


    public void setAppContext(AppContext appContext) {
        this.appContext = appContext;
    }


    public abstract void forward();


    public abstract void collect();


    public abstract void comment(String comment);

}

