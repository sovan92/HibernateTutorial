Absolutely! Let’s walk through how to implement a **custom ID generator in Hibernate** that increments IDs by `100` (or any step you want).

---

## 🛠️ Goal

You want to generate IDs like:

```
1000, 1100, 1200, ...
```

instead of:

```
1000, 1001, 1002, ...
```

---

## ✅ Step-by-Step: Custom ID Generator in Hibernate

---

### 1️⃣ Create the Custom Generator Class

```java
package com.tutorial.sovan92.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class CustomStepIdGenerator implements IdentifierGenerator {

    private static final AtomicLong sequence = new AtomicLong(1000); // Starting value
    private static final long STEP = 100; // Increment size

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return sequence.getAndAdd(STEP);
    }
}
```

> 🔁 This is a **simple in-memory counter**. For production, you’d implement a **DB-backed version**.

---

### 2️⃣ Use It in Your Entity with `@GenericGenerator`

```java
package com.tutorial.sovan92.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue(generator = "custom_step_generator")
    @GenericGenerator(
        name = "custom_step_generator",
        strategy = "com.tutorial.sovan92.generator.CustomStepIdGenerator"
    )
    private Long id;

    private String name;
}
```

---

### 3️⃣ Save and Test

```java
@Test
void testCustomIdGeneration() {
    Hospital h1 = hospitalRepository.save(Hospital.builder().name("Apollo").build());
    Hospital h2 = hospitalRepository.save(Hospital.builder().name("Fortis").build());

    System.out.println("ID 1: " + h1.getId()); // Should be 1000
    System.out.println("ID 2: " + h2.getId()); // Should be 1100
}
```

---

## 🔐 Notes

| Feature                 | Status         |
|------------------------|----------------|
| Thread-safe generation | ✅ Yes (AtomicLong) |
| Persistent across app restarts | ❌ No (in-memory only) |
| Good for learning/demo | ✅ Absolutely |

---

## 🚀 Want It to Persist Across Restarts?

Instead of `AtomicLong`, you'd:

- Read/write to a **sequence table in MySQL**
- Lock and increment value manually (or use `select for update`)
- Cache block of IDs if needed

Let me know if you want the **advanced DB-backed version** too!

---

## ✅ Summary

You now have a working custom Hibernate ID generator that:
- Starts from 1000
- Increments by 100
- Requires no table setup
- Is great for testing and learning

Would you like me to ZIP a full Spring Boot project with this setup?