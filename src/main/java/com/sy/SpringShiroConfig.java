package com.sy;


import com.sy.bmq.common.realm.MyJdbcRealm;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringShiroConfig {
//
//    @Value(value = "casClient")
//    private String clientName;
//    /**
//     * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions),
//     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
//     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
//     */
//    @Bean
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }

    /**
     * 开启aop注解支持
     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Autowired SecurityManager securityManager) {
//
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }



    @Bean
    public Realm createRealm(@Autowired DataSource dataSource){

        MyJdbcRealm realm = new MyJdbcRealm();
        realm.setDataSource(dataSource);
        realm.setPermissionsLookupEnabled(true);
        return realm;
    }

    /**
     * 使用自定义认证及授权逻辑
     * @return
     */
    /*@Bean(name = "authorizer")
    public CasRealm pac4jRealm(){
        CasRealm casRealm = new CasRealm();
        return casRealm;
    }*/

    //Filter工厂，设置对应的过滤条件和跳转条件
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired SecurityManager securityManager) {
//        System.out.println( "shiro Filter....." );
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        shiroFilterFactoryBean.setLoginUrl("http://127.0.0.1:8020/static/login.html");
//        //拦截器.
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//
//        // 配置不会被拦截的链接 顺序判断
//        filterChainDefinitionMap.put("/login.do", "anon");
//        filterChainDefinitionMap.put("/captcha.do", "anon");
//        filterChainDefinitionMap.put("/tologin.do", "anon");
//        filterChainDefinitionMap.put("/session.do", "anon");
//        //filterChainDefinitionMap.put("/user/remember.do", "user");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
       /* ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //1.设置认证管理器使用的Realm和认证交由CAS处理
        DefaultWebSecurityManager sm = (DefaultWebSecurityManager) securityManager;
        //增加pac4jSubjectFactory,认证管理交由Cas
        sm.setSubjectFactory(pac4jSubjectFactory);
        //认证和授权
        sm.setRealm(casRealm);
        shiroFilterFactoryBean.setSecurityManager(sm);
        shiroFilterFactoryBean.setLoginUrl("http://127.0.0.1:8080/cas/");
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
        filterChainDefinitionMap.put("/login.do", "anon");
        filterChainDefinitionMap.put("/captcha.do", "anon");
        filterChainDefinitionMap.put("/tologin.do", "anon");
        filterChainDefinitionMap.put("/session.do", "anon");
        //filterChainDefinitionMap.put("/user/remember.do", "user");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;*/
    }

    /**
     * 使用 pac4j 的 subjectFactory
     * @return
     */
   /* @Bean
    public Pac4jSubjectFactory subjectFactory(){
        return new Pac4jSubjectFactory();
    }*/
//}
