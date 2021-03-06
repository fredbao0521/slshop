package com.sy.bmq.common.realm;

import com.sy.bmq.model.AuAuthority;
import com.sy.bmq.model.Func;
import com.sy.bmq.model.User;
import com.sy.zy.service.AuthService;
import com.sy.zy.service.FuncService;
import com.sy.zy.service.UserService ;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jPrincipal;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

public class CasRealm extends Pac4jRealm {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private FuncService funcService;
    /**
     * 认证,使用CAS返回ticket认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        final Pac4jToken pac4jToken = (Pac4jToken) authenticationToken;
        final List<CommonProfile> commonProfileList = pac4jToken.getProfiles();
        final CommonProfile commonProfile = commonProfileList.get(0);
        System.out.println("单点登录返回的信息=====>" + commonProfile.toString());
        System.out.println("用户名为********"+commonProfile.getId());
        final Pac4jPrincipal principal = new Pac4jPrincipal(commonProfileList, getPrincipalNameAttribute());
        final PrincipalCollection principalCollection = new SimplePrincipalCollection(principal, getName());
        return new SimpleAuthenticationInfo(principalCollection, commonProfileList.hashCode());
    }

    /**
     * 授权,使用shiro的授权方式
     * 应该获取CAS返回用户信息,去数据库中查询相应权限信息,权限管理交由shiro
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("======>doGetAuthorizationInfo");
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        Pac4jPrincipal principal = (Pac4jPrincipal)this.getAvailablePrincipal(principals);
        User user=new User();
        user.setUsername(principal.getProfile().getId());
        try {
            List<User> users = userService.selectAll(user);
            User user1 = users.get(0);
            List<AuAuthority> auAuthorities = authService.find(user1.getRoleId());
            for (AuAuthority a:auAuthorities) {
                List<Func> byRoleId = funcService.findByRoleId(a.getFunctionId());
                for (Func f:byRoleId) {
                    authInfo.addStringPermission(f.getFuncUrl());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------------------"+principal.getProfile().getId());
        try {
            System.out.println(dataSource.getConnection());
        }catch (Exception e){
            e.printStackTrace();
        }
        return authInfo;

    }
}
