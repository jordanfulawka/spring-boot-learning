package ca.jordanfulawka.aopdemo.aspect;

import ca.jordanfulawka.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* ca.jordanfulawka.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @Around on method: " + method);
        long begin = System.nanoTime();

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        long end = System.nanoTime();
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration + " nanoseconds");
        return result;
    }


    @After("execution(* ca.jordanfulawka.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* ca.jordanfulawka.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theExc) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @AfterThrowing on method: " + method);


        System.out.println("\n====>>> the exception is: " + theExc);
    }

    @AfterReturning(
            pointcut = "execution(* ca.jordanfulawka.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @AfterReturning on method: " + method);
        System.out.println("\n====>>> result is: " + result);
        // post-process the data, we will modify it
        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        System.out.println("\n====>>> updated result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for(Account account : result) {
            String uppername = account.getName().toUpperCase();
            account.setName(uppername);
        }
    }

    @Before("ca.jordanfulawka.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("======>>> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments
        Object[] args = joinPoint.getArgs();

        for(Object arg : args) {
            System.out.println(arg);

            if(arg instanceof Account) {
                //downcast and print Account specific stuff
                Account account = (Account) arg;
                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());

            }
        }
    }

}
