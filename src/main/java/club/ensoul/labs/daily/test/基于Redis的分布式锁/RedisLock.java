// package club.ensoul.labs.daily.test.基于Redis的分布式锁;
//
// import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
//
// import java.util.Collections;
//
// public class RedisLock {
//
//     private static final int TIME = 1000;
//     private static final int DEFAULT_SLEEP_TIME = 1000;
//
//     private static final String SET_IF_NOT_EXIST = "NX";
//     private static final String SET_WITH_EXPIRE_TIME = "PX";
//
//     private static final String LOCK_PREFIX = "LOCK_PREFIX_";
//     private static final String LOCK_MSG = "LOCK_MSG";
//     private static final String UNLOCK_MSG = "UNLOCK_MSG";
//
//     // 非阻塞锁
//     public boolean tryLock(String key, String request) {
//         String result = this.jedis.set(LOCK_PREFIX + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
//         if(LOCK_MSG.equals(result)) {
//             return true;
//         } else {
//             return false;
//         }
//     }
//
//
//     // 阻塞锁
//     public void lock(String key, String request) throws InterruptedException {
//         for(; ; ) {
//             String result = this.jedis.set(LOCK_PREFIX + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
//             if(LOCK_MSG.equals(result)) {
//                 break;
//             }
//             //防止一直消耗 CPU
//             Thread.sleep(DEFAULT_SLEEP_TIME);
//         }
//     }
//
//     // 自定义阻塞时间锁
//     public boolean lock(String key, String request, int blockTime) throws InterruptedException {
//         while(blockTime >= 0) {
//             String result = this.jedis.set(LOCK_PREFIX + key, request, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10 * TIME);
//             if(LOCK_MSG.equals(result)) {
//                 return true;
//             }
//             blockTime -= DEFAULT_SLEEP_TIME;
//             Thread.sleep(DEFAULT_SLEEP_TIME);
//         }
//         return false;
//     }
//
//     /**
//      * 解锁
//      * 解锁也很简单，其实就是把这个 key 删掉就万事大吉了，比如使用 del key 命令，但现实往往没有那么 easy。
//      * 如果进程 A 获取了锁设置了超时时间，但是由于执行周期较长导致到了超时时间之后锁就自动释放了。这时进程 B 获取了该锁执行很快就释放锁。这样就会出现进程 B 将进程 A 的锁释放了。
//      * 所以最好的方式是在每次解锁时都需要判断锁是否是自己的。 这时就需要结合加锁机制一起实现了。
//      * 加锁时需要传递一个参数，将该参数作为这个 key 的 value，这样每次解锁时判断 value 是否相等即可。 所以解锁代码就不能是简单的 del了。
//      *
//      * @param key
//      * @param request
//      * @return
//      */
//     public boolean unlock(String key, String request) {
//
//         // 这里使用了一个 lua 脚本来判断 value 是否相等，相等才执行 del 命令，使用 lua 也可以保证这里两个操作的原子性。
//         String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//         Object result = null;
//
//         if(jedis instanceof Jedis) {
//             result = ((Jedis) this.jedis).eval(script, Collections.singletonList(LOCK_PREFIX + key), Collections.singletonList(request));
//         } else if(jedis instanceof JedisCluster) {
//             result = ((JedisCluster) this.jedis).eval(script, Collections.singletonList(LOCK_PREFIX + key), Collections.singletonList(request));
//         } else {
//             //throw new RuntimeException("instance is error") ;
//             return false;
//         }
//
//         if(UNLOCK_MSG.equals(result)) {
//             return true;
//         } else {
//             return false;
//         }
//     }
//
//
// }
