Awesome question — you're thinking exactly like a Hibernate power user! 💪

### ✅ Your Goal:
> When you call `hospitalRepository.save(hospital)` twice, you want the generated IDs to be **1000, 1100, 1200...** (i.e., increment by 100), **not 1001, 1002...**

---

## 🔍 Why You're Getting 1001, 1002

Even though you have this:

```java
allocationSize = 100
```

Hibernate **still assigns IDs in increments of 1** by default **within each block of 100**, meaning:
- It **reserves** 100 IDs in memory (say 1000–1099),
- But assigns them sequentially: `1000, 1001, 1002...` etc.

> 🧠 Hibernate uses `allocationSize` for **efficiency**, not for actual ID incrementing.

---

## ✅ How to Generate IDs Like 1000, 1100, 1200...

You have **two good options**:

---

### ✅ Option 1: Manually control the ID value based on `allocationSize`

1. Change the allocation to 1:
   ```java
   @TableGenerator(
       name = "employee_gen",
       table = "id_gen",
       pkColumnName = "gen_name",
       valueColumnName = "gen_val",
       initialValue = 1000,
       allocationSize = 1
   )
   ```
2. Then inside your test or logic, **set the ID manually by multiplying the count**:
   ```java
   for (int i = 0; i < 3; i++) {
       Hospital hospital = Hospital.builder()
           .id(1000L + i * 100)  // 1000, 1100, 1200...
           .name("Hospital " + i)
           .build();
       hospitalRepository.save(hospital);
   }
   ```

> ✅ Works, but you're giving up `@GeneratedValue` automation

---

### ✅ Option 2 (🔥 Advanced & Automatic): Custom `@GenericGenerator`

If you want **auto-generated IDs that jump by 100** on each save, you’ll need to:

1. **Write a custom ID generator**
2. Register it using Hibernate's `@GenericGenerator`

Let me know if you'd like me to code this custom solution for you — it’s very reusable and looks like this:

```java
@GenericGenerator(
    name = "custom_sequence_generator",
    strategy = "com.example.generator.StepSequenceGenerator",
    parameters = {
        @Parameter(name = "increment_size", value = "100"),
        ...
    }
)
```

---

## 🚀 Recommended Approach (for learning)

If you're experimenting with `@TableGenerator` for **demo purposes**, and want to see `1000, 1100, 1200...`, then I recommend:

```java
@TableGenerator(
    name = "employee_gen",
    table = "id_gen",
    pkColumnName = "gen_name",
    valueColumnName = "gen_val",
    initialValue = 1000,
    allocationSize = 100
)
@GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_gen")
```

And just pick **one ID per block** to verify:
```java
Hospital h1 = hospitalRepository.save(Hospital.builder().name("A").build());
Hospital h2 = hospitalRepository.save(Hospital.builder().name("B").build());

System.out.println(h1.getId()); // 1000
System.out.println(h2.getId()); // 1001 (still within block)
```

But if you want each insert to be `+100`, use a **custom generator** or set IDs manually.

---

## ✅ Summary

| Option                         | Behavior                       |
|-------------------------------|--------------------------------|
| `allocationSize = 100`        | Reserves blocks of 100, but IDs still auto-increment (`1000, 1001, 1002...`) |
| Manually set `.id(1000 + n*100)` | Total control over ID value |
| Custom ID Generator           | Auto-generate `1000, 1100, 1200...` (requires extra class) |

---

Would you like me to build and share a reusable custom Hibernate `IdentifierGenerator` that auto-increments IDs by 100?