package lab8;


import java.util.LinkedList;


// хранит список всех url-адресов для поиска, их глубину.
public class URLPool {
     LinkedList<URLDepthPair> findLink;
     LinkedList<URLDepthPair> viewedLink;
    private int maxDepth;
    private int cWait;
    public URLPool(int maxDepth) {
        this.maxDepth = maxDepth;
        findLink = new LinkedList<URLDepthPair>();
        viewedLink = new LinkedList<URLDepthPair>();
        cWait = 0;
    }


    //Синхронизируем объекты пула юрл-адресов во всех критических точках
    //Synchronized - это ключевое слово,
    // которое позволяет заблокировать доступ к методу или части кода,
    // если его уже использует другой поток.


    //получить пару и сразу удалить. если она недоступна, то ждем.
    // также здесь считаем сколько потоков простаивает в режиме ожидания.
    public synchronized URLDepthPair getPair() {
        while (findLink.size() == 0) {
            cWait++;
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println("InterruptedException is ignored");
            }
            cWait--;
        }
//        URLDepthPair nextPair = findLink.removeFirst();
//        return nextPair;
        return findLink.removeFirst();
    }

    //добавить пару, перед этим проверить на существование в массиве viewedLink
    public synchronized void addPair(URLDepthPair pair)
    {
        if(URLDepthPair.check(viewedLink,pair))
        {
            viewedLink.add(pair);
            if (pair.getDepth() < maxDepth)
            {
                findLink.add(pair);
                notify();
            }
        }
    }

    //возвращает колво воркеров в ожидании
    public synchronized int getWait()
    {
        return cWait;
    }
    public LinkedList<URLDepthPair> getResult()
    {
        return viewedLink;
    }
}
