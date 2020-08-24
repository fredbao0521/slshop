package com.sy.bmq.realm;



import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class MyJdbcRealm extends JdbcRealm {

/**
     * 用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException*/


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("1.doGetAuthenticationInfo.........");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = null;
        Connection connection = null;
        if (username == null) {
            throw new AccountException("用户名不能为空");
        } else {
            try {
                connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("select password from au_user where username =?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    password = resultSet.getString("password");
                }
                if (password == null) {
                    throw new AccountException("用户名不存在");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
        return new SimpleAuthenticationInfo(username, password.toCharArray(), this.getName());
    }

/**
     * 用户授权
     *
     * @param principals
     * @return*/


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("2.doGetAuthorizationInfo.........");
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        } else {
            String username = (String) this.getAvailablePrincipal(principals);
            Connection conn = null;
            Set<String> roleNames = new HashSet<>();
            Set permissions = new HashSet();

            try {
                //1.先查角色
                conn = this.dataSource.getConnection();
                PreparedStatement statement = conn.prepareStatement("SELECT rolecode FROM au_role WHERE id = (SELECT roleid FROM au_user WHERE username = ?)");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String rolename = resultSet.getString("rolecode");
                    roleNames.add(rolename);
                }

                //2.再查权限
                statement = conn.prepareStatement("SELECT funcUrl FROM au_function WHERE id " +
                        "IN(SELECT functionid FROM au_authority WHERE roleid =(" +
                        "SELECT roleid FROM au_user WHERE username = ?))");
                statement.setString(1, username);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String permission = resultSet.getString("funcUrl");
                    System.out.println(permission);
                    permissions.add(permission);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
            info.setStringPermissions(permissions);
            return info;
        }

    }
}

