package ca.jordanfulawka.aopdemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class LuvAopExpressions {

    @Pointcut("execution(* ca.jordanfulawka.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution (* ca.jordanfulawka.aopdemo.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution (* ca.jordanfulawka.aopdemo.dao.*.set*(..))")
    public void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
