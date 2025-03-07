package kr.co.dysnt.framework.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
@Aspect
@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class TransactionConfig {

    private final PlatformTransactionManager txManager;

    private static final int TX_METHOD_TIMEOUT = 100000;
    private static final String AOP_POINTCUT_EXPRESSION = "(execution(* *..*.service..*.*(..)) " +
            "|| execution(* *..*.services..*.*(..))) " +
            "|| (execution(* kr.co.dysnt.framework.**.service..*.*(..)))";

    @Bean
    public TransactionInterceptor txAdvice() {

        TransactionInterceptor txAdvice = new TransactionInterceptor();

        List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));

        DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(
                TransactionDefinition.PROPAGATION_REQUIRED);
        readOnlyAttribute.setReadOnly(true);
        readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);

        RuleBasedTransactionAttribute dmlSqlAttribute = new RuleBasedTransactionAttribute(
                TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
        dmlSqlAttribute.setTimeout(TX_METHOD_TIMEOUT);

        String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
        String dmlSqlTransactionAttributesDefinition = dmlSqlAttribute.toString();

        log.debug("READ ONLY    ATTRIBUTES :: {}", readOnlyTransactionAttributesDefinition);
        log.debug("WRITE        ATTRIBUTES :: {}", dmlSqlTransactionAttributesDefinition);

        Properties txAttributes = new Properties();

        // read-only
        txAttributes.setProperty("*retrieve*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("*select*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("*get*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("*list*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("*search*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("*find*", readOnlyTransactionAttributesDefinition);
        txAttributes.setProperty("*count*", readOnlyTransactionAttributesDefinition);

        // write rollback-rule
        txAttributes.setProperty("*", dmlSqlTransactionAttributesDefinition);

        txAdvice.setTransactionAttributes(txAttributes);
        txAdvice.setTransactionManager(txManager);

        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
