package org.multipoly;

import org.multipoly.Board.Edward;
import org.umlg.runtime.adaptor.UMLG;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date: 2014/12/02
 * Time: 12:28 PM
 */
public class MExecutorService<T> {

    public final static Map<ThreadPoolExecutor, String> pools = new ConcurrentHashMap<>();
    private ThreadPoolExecutor executorService;
    private ScheduledExecutorService scheduledExecutorService;
    private ExecutorCompletionService executorCompletionService;

    private MExecutorService(ThreadPoolExecutor executorService, ExecutorCompletionService executorCompletionService) {
        UMLG.get();
        this.executorService = executorService;
        this.executorCompletionService = executorCompletionService;
    }

    private MExecutorService(ScheduledExecutorService scheduledExecutorService) {
        UMLG.get();
        this.scheduledExecutorService = scheduledExecutorService;
    }
    
    

    /*public static MExecutorService createScheduledThreadPool(String serviceName, int nThreads) {
        ScheduledExecutorService executorService = new MScheduledThreadPoolExecutor(
                nThreads,
                new CmExecutorServiceThreadFactory(serviceName)
        );
        MExecutorService mExecutorService = new MExecutorService(executorService);
        MExecutorService.pools.put((ThreadPoolExecutor) executorService, serviceName);
        return mExecutorService;
    }*/

    public static MExecutorService createCompletionService(String serviceName, int nThreads) {
        ThreadPoolExecutor executorService = new MThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new MExecutorServiceThreadFactory(serviceName));
        MExecutorService mExecutorService = new MExecutorService(executorService, new ExecutorCompletionService(executorService));
        MExecutorService.pools.put(executorService, serviceName);
        return mExecutorService;
    }

    public void shutdown() {
        Edward edward  = new Edward();
        edward.getBoard()
                e
        if (this.executorService != null)
            this.executorService.shutdown();
        if (this.scheduledExecutorService != null)
            this.scheduledExecutorService.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.executorService.shutdownNow();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.executorService.awaitTermination(timeout, unit);
    }

    public boolean isTerminated() {
        return this.executorService.isTerminated();
    }

    public Future<T> submit(Callable<T> task) {
        return this.executorCompletionService.submit(task);
    }

    public ScheduledFuture<?> schedule(Runnable command,
                                       long initialDelay,
                                       TimeUnit unit) {
        if (this.scheduledExecutorService == null) {
            throw new IllegalStateException("To schedule a service you must call createScheduledThreadPool");
        }
        return this.scheduledExecutorService.schedule(command, initialDelay, unit);
    }

    public Future<T> take() throws InterruptedException {
        return this.executorCompletionService.take();
    }

    private static class MThreadPoolExecutor extends ThreadPoolExecutor {

        public MThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        @Override
        protected void terminated() {

            MExecutorService.pools.remove(this);
        }
    }

    private static class MExecutorServiceThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public MExecutorServiceThreadFactory(String prefix) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = prefix + "-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public ThreadPoolExecutor getExecutorService() {
        return executorService;
    }

}
