package cn.edu.hdu.passbook.security;

/**
 * <h1>用 ThreadLocal 单独存储每一个线程携带的 Token 信息</h1>
 * @author samy
 * @date 2019/11/23 15:27
 */
public class AccessContext {

    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static String getToken(){
        return token.get();
    }

    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }

    public static void clearAccessKey(){
        token.remove();
    }

}
