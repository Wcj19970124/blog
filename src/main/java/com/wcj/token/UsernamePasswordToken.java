

package com.wcj.token;

/**
 * 因为realm在判断是后台管理员还是前台用户
 * 的时候，需要先有用户的信息才能进行后台管理员登录，
 * 所以我们重写其这个方法，加上一个标识是后台管理员
 * 还是前台用户的字段，用来进行判断，而不是根据登录
 * 用户的类型进行判断，这样登陆之前会报null
 * @author 邬成军
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    /**
     * 标识后台管理员和前台用户的字段
     */
    private Integer state;

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, char[] password) {
        super(username, (char[])password, false, (String)null);
    }

    public UsernamePasswordToken(String username, String password,Integer state) {
        super(username, (char[])(password != null ? password.toCharArray() : null), false, (String)null);
        this.state= state;
    }

    public UsernamePasswordToken(String username, char[] password, String host) {
        super(username, password, false, host);
    }

    public UsernamePasswordToken(String username, String password, String host) {
        super(username, password != null ? password.toCharArray() : null, false, host);
    }

    public UsernamePasswordToken(String username, char[] password, boolean rememberMe) {
        super(username, (char[])password, rememberMe, (String)null);
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe) {
        super(username, (char[])(password != null ? password.toCharArray() : null), rememberMe, (String)null);
    }

    public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host) {
        super(username,password,rememberMe,host);
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
