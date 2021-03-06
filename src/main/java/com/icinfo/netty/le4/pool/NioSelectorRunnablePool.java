package com.icinfo.netty.le4.pool;

import com.icinfo.netty.le4.NioServerBoss;
import com.icinfo.netty.le4.NioServerWorker;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:  <br>
 * <p>
 * author jkk
 * date 2018年10月02
 */
public class NioSelectorRunnablePool {
    private final AtomicInteger bossIndex=new AtomicInteger();
    private Boss[] bosses;
    private final AtomicInteger workerIndex=new AtomicInteger();
    private Worker[] workers;

    public NioSelectorRunnablePool(Executor boss,Executor worker){
        initBoss(boss,1);
        initWorker(worker,Runtime.getRuntime().availableProcessors()*2);
    }

    /**
     * 初始化worker线程
     * @param worker
     * @param count
     */
    private void initWorker(Executor worker, int count) {
        this.workers=new NioServerWorker[count];
        for (int i=0;i<workers.length;i++){
            workers[i]=new NioServerWorker(worker,"worker thread"+(i+1),this);
        }
    }

    /**
     * 初始化boss线程
     * @param boss
     * @param count
     */
    private void initBoss(Executor boss, int count) {
        this.bosses = new NioServerBoss[count];
        for (int i=0;i<bosses.length;i++){
            bosses[i]=new NioServerBoss(boss,"boss thread "+(i+1),this);
        }
    }

    /**
     * 获取一个worker
     * @return
     */
    public Worker nextWorker(){
        return workers[Math.abs(workerIndex.getAndIncrement()%workers.length)];
    }

    /**
     * 获取一个boss
     * @return
     */
    public Boss nextBoss(){
        return bosses[Math.abs(bossIndex.getAndIncrement()%bosses.length)];
    }
}
