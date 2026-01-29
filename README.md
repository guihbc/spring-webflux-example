## Reactive Programming
Reactive programming is about building systems that react to events as they happen, handling asynchronous data streams efficiently without blocking threads.

Traditional (blocking) approach:

```kotlin
// Thread waits here doing nothing
val user = userRepository.findById(id)  // Blocks for 100ms
val orders = orderRepository.findByUser(user.id)  // Blocks for 200ms
return orders
// Total: 300ms of thread blocking
```

Reactive (non-blocking) approach:

```kotlin
// Thread is freed immediately, work happens asynchronously
return userRepository.findById(id)  // Returns immediately
.flatMap { user -> orderRepository.findByUser(user.id) }
// Thread can handle other requests while waiting
```

The key insight: instead of blocking a thread while waiting for I/O (database, HTTP calls, file reads),
reactive systems use callbacks and event loops to do other work. One thread can handle thousands of concurrent requests.

## Spring WebFlux
Spring WebFlux is Spring's reactive web framework built on Project Reactor (which provides Mono and Flux).
Key components:
Mono and Flux - The building blocks:

- `Mono<T>`: 0 or 1 element (like Optional but async)
- `Flux<T>`: 0 to N elements (like a stream but async)

Reactive repositories:
```kotlin
interface UserRepository : ReactiveCrudRepository<User, Long> {
fun findByEmail(email: String): Mono<User>
fun findAll(): Flux<User>
}
```

Reactive controllers:
```kotlin
@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): Mono<User> {
        return userService.findById(id)
    }
    
    @GetMapping
    fun getAllUsers(): Flux<User> {
        return userService.findAll()
    }
}
```

When to use WebFlux:

✅ Good for:
- High concurrency with I/O-bound operations (microservices, API gateways)
- Streaming data (real-time updates, SSE, WebSockets)
- Systems with many slow external calls (databases, APIs)
- Limited resources (handle more requests with fewer threads)

❌ Not ideal for:
- CPU-intensive work (blocking operations defeat the purpose)
- Simple CRUD apps with low traffic
- When your dependencies are all blocking (JDBC, blocking libraries)
- Teams unfamiliar with reactive - steep learning curve