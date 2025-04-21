
# Exceptions encountered while studying this topic . 


I created the employee class and employee repository . 


### Employee Class
```aiignore

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
}


```

### EmployeeRepository . 
```aiignore
public interface EmployeeRepository extends CrudRepository<Employee, Integer> { }
```

### Test method

```aiignore
@Test
	void testCreateEmployee(){

		Employee employee =
				Employee.builder()
						.id(4)
						.name("beta")
						.email("<EMAIL>")
						.build();
		employeeRepository.save(employee);
}
```

```aiignore
org.springframework.orm.ObjectOptimisticLockingFailureException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [com.tutorial.sovan92.entities.Employee#4]

	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.convertHibernateAccessException(HibernateJpaDialect.java:325)
	at org.springframework.orm.jpa.vendor.HibernateJpaDialect.translateExceptionIfPossible(HibernateJpaDialect.java:244)
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.translateExceptionIfPossible(AbstractEntityManagerFactoryBean.java:560)
	at org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:61)
	at org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:343)
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:160)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:165)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223)
	at jdk.proxy2/jdk.proxy2.$Proxy120.save(Unknown Source)
	at com.tutorial.sovan92.Sovan92HibernateTutorialApplicationTests.testCreateEmployee(Sovan92HibernateTutorialApplicationTests.java:111)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
Caused by: org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): [com.tutorial.sovan92.entities.Employee#4]
	at org.hibernate.event.internal.DefaultMergeEventListener.entityIsDetached(DefaultMergeEventListener.java:426)
	at org.hibernate.event.internal.DefaultMergeEventListener.merge(DefaultMergeEventListener.java:214)
	at org.hibernate.event.internal.DefaultMergeEventListener.doMerge(DefaultMergeEventListener.java:152)
	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:136)
	at org.hibernate.event.internal.DefaultMergeEventListener.onMerge(DefaultMergeEventListener.java:89)
	at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:127)
	at org.hibernate.internal.SessionImpl.fireMerge(SessionImpl.java:854)
	at org.hibernate.internal.SessionImpl.merge(SessionImpl.java:840)



```
Absolutely! Here's a clean, markdown-formatted version of the explanation for your reference:

---

# 📌 Understanding `ObjectOptimisticLockingFailureException` in JPA

## ❗ The Exception

```text
org.springframework.orm.ObjectOptimisticLockingFailureException:
Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect): 
[com.tutorial.sovan92.entities.Employee#4]
```

This means:

> Hibernate thinks you're updating an entity with ID `4`, but it can't find it in the database.

---

## 🔍 Why It Happened

In your test method, you wrote:

```java
Employee employee = Employee.builder()
        .id(4) // ❌ Manually setting the ID
        .name("beta")
        .email("beta@example.com")
        .build();

employeeRepository.save(employee);
```

Your `Employee` entity is defined with:

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
```

So when you manually set the ID (`4`), Hibernate treats it as a **detached** entity that should be **updated**. Since it doesn’t exist in the database, Hibernate throws:

> `StaleObjectStateException` → Internally wrapped by `ObjectOptimisticLockingFailureException`

---

## ✅ The Correct Way to Save a New Entity

Don't set the ID manually. Let the database generate it:

```java
@Test
void testCreateEmployee() {

    Employee employee = Employee.builder()
            .name("beta")
            .email("beta@example.com")
            .build(); // ✅ No ID set

    employeeRepository.save(employee);
}
```

---

## 💡 Bonus Tip: Use `Integer` Instead of `int`

Change your entity to:

```java
private Integer id; // ✅ Nullable, better for JPA
```

This avoids issues where `int` defaults to `0`, which can confuse Hibernate.

---

## ✅ Summary

| Problem                            | Fix                                           |
|-----------------------------------|-----------------------------------------------|
| Manually setting `.id(4)`         | ❌ Hibernate assumes entity already exists     |
| Letting JPA generate the ID       | ✅ Omit `.id()` in builder                     |
| Primitive `int` field             | ✅ Use `Integer` for nullable auto-generated ID|

---

Let me know if you’d like a similar markdown note for **updating existing entities**!
