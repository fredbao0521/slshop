package com.sy;

import com.sy.bmq.common.realm.CasRealm;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.pac4j.core.config.Config;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Value(value = "casClient")
    private String clientName;

    //1.对shiro注解的支持(Controller动态代理对像创建的一个组件,Shiro切面的组件)
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        //CGLIB动态代理
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 启动shiro的过滤器
     * 并向shiro过滤器中添加CAS过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired SecurityManager securityManager,
                                                         @Autowired Config exPac4jConfig,
                                                         @Autowired Pac4jSubjectFactory pac4jSubjectFactory,
                                                         @Autowired CasRealm casRealm) {
        System.out.println( "shiro++++++++++++++++++++++++++" );
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //1.设置认证管理器使用的Realm和认证交由CAS处理
        DefaultWebSecurityManager sm = (DefaultWebSecurityManager) securityManager;
        //增加pac4jSubjectFactory,认证管理交由Cas
        sm.setSubjectFactory(pac4jSubjectFactory);
        //认证和授权
        sm.setRealm(casRealm);
        shiroFilterFactoryBean.setSecurityManager(sm);

        //2.向shiro过滤器中添加额外的CAS过滤器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();

        // cas 资源认证过滤器
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setConfig(exPac4jConfig);
        securityFilter.setClients(clientName);
        filters.put("securityFilter", securityFilter);
        //cas 认证后回调过滤器
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(exPac4jConfig);
        filters.put("callbackFilter", callbackFilter);
        shiroFilterFactoryBean.setFilters(filters);
        //本地登出同步登出CAS服务器
        LogoutFilter pac4jCentralLogout = new LogoutFilter();
        pac4jCentralLogout.setConfig(exPac4jConfig);
        pac4jCentralLogout.setCentralLogout(true);
        pac4jCentralLogout.setLocalLogout(true);
        filters.put("logoutFilter", pac4jCentralLogout);
        //定义三个CAS过滤器对特定路径的过滤
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //访问什么资源都需cas认证
        filterChainDefinitionMap.put("/logout", "logoutFilter");//CAS登出
        filterChainDefinitionMap.put("/callback", "callbackFilter");//CAS认证成功,回调原访问路径
        filterChainDefinitionMap.put("/**", "securityFilter");//CAS认证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    /**
     * 使用自定义认证及授权逻辑
     * @return
     */
    @Bean(name = "authorizer")
    public CasRealm pac4jRealm(){
        CasRealm casRealm = new CasRealm();
        return casRealm;
    }



    /**
     * 使用 pac4j 的 subjectFactory
     * @return
     */
    @Bean
    public Pac4jSubjectFactory subjectFactory(){
        return new Pac4jSubjectFactory();
    }


}
