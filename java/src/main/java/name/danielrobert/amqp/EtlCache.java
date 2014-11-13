package name.danielrobert.amqp;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EtlCache {
    private final ConcurrentMap<EtlExecution.Result, AtomicInteger> cache = new ConcurrentHashMap<>();
    
    public void cache(EtlExecution etl) {
        cache.putIfAbsent(etl.getResult(), new AtomicInteger());
        cache.get(etl.getResult()).incrementAndGet();
    }

    public int countByResult(EtlExecution.Result result) {
        AtomicInteger ret = cache.get(result);
        return (null == ret) ? 0 : ret.get();
    }
    
    public void flush() {
        cache.clear();
    }
}
