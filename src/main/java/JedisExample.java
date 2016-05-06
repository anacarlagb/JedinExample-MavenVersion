import redis.clients.jedis.Jedis;

import java.util.Arrays;

//Fonte : https://www.javacodegeeks.com/2013/10/getting-started-with-jedis.html
public class JedisExample {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("localhost");
        //Example 1
        jedis.flushAll();

        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println("Valor de foo: " + value);

        for (int i = 1; i <= 10; i++) {
            jedis.rpush("intlist",String.valueOf(i));
        }

        System.out.println("Lista de inteiros: " + Arrays.toString(jedis.lrange("intlist", 0, -1).toArray()));

        //Example 2
        System.out.println(jedis.get("counter"));
        jedis.incr("counter");
        System.out.println(jedis.get("counter"));

        //Example 3

        //adding a new key
        String cacheKey = "cachekey";
        jedis.set(cacheKey, "cached value");
        //setting the TTL in seconds
        jedis.expire(cacheKey, 15);
        //Getting the remaining ttl
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        Thread.sleep(1000);
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        //Getting the cache value
        System.out.println("Cached Value:" + jedis.get(cacheKey));
        //Wait for the TTL finishs
        Thread.sleep(15000);
        //trying to get the expired key
        System.out.println("Expired Key:" + jedis.get(cacheKey));

        //Example 4
//        String cacheKey = "languages";
//        //Adding a set as value
//        jedis.sadd(cacheKey,"Java","C#","Python");//SADD
//
//        //Getting all values in the set: SMEMBERS
//        System.out.println("Languages: " + jedis.smembers(cacheKey));
//        //Adding new values
//        jedis.sadd(cacheKey,"Java","Ruby");
//        //Getting the values... it doesn't allow duplicates
//        System.out.println("Languages: " + jedis.smembers(cacheKey));


    }


}