package lab8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.*;

//Кравлер-таск реализует интерфейс Runnable, что позволяет выполнить
// веб-сканирование в нескольких потоках.
public class CrawlerTask implements Runnable {
    URLPool urlPool;
//    private static final String URL_PREFIX = "<a href=\"http";
    // получение пары из юрл-пула (ожидает, если пара
    // не сразу будет доступна)
    public CrawlerTask(URLPool pool){
        urlPool = pool;
    }
    //получаем страницу по юрл адресу
    public static void request(PrintWriter out,URLDepthPair pair) throws MalformedURLException {
//        String request = "GET " + pair.getPath() + " HTTP/1.1\r\nHost:" + pair.getHost() + "\r\nConnection: Close";

//        out.println(request);
//        out.flush();
        out.println("GET " + pair.getPath() + " HTTP/1.1");
        out.println("Host: " + pair.getHost());
        out.println("Connection: close");
        out.println();
        out.flush();


        System.out.println("FEDFNSEHOSFHSODHAWDHAPSCDA");
        System.out.println(pair.getPath());
        System.out.println(pair.getHost());

    }
    // здесь метод: для каждого найденного юрл добавляем новую пару к
    // пулу юрл-адресов. Новая пара имеет глубину на единицу больше,
    // чем глубина текущего юрл-адреса (по кот. происсходит сканирование)
    public static void buildNewUrl(String str,int depth,URLPool pool){
        try {
            String currentLink = str.substring(str.indexOf(URLDepthPair.URL_PREFIX)+9,str.indexOf("\"", str.indexOf(URLDepthPair.URL_PREFIX)+9));
            pool.addPair(new URLDepthPair(currentLink, depth + 1));
        }
        catch (StringIndexOutOfBoundsException e) {}
    }








    @Override
    public void run() {
        while (true) {
            //получение пары из пула
            URLDepthPair currentPair = urlPool.getPair();
            try {
                //создаем сокет и получаем веб-страницу
                Socket socket = new Socket(currentPair.getHost(), 80);
                socket.setSoTimeout(10000);
                try {
                    //поток вывода, экземпляр данного класса нужен для вызова метода
                    //println
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    //для чтения целых строк (с объектами типа .. для потоков ввода)
                    BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    request(out,currentPair);
                    String line = in.readLine();
                    while (line != null){
                        if (line.contains(URLDepthPair.URL_PREFIX) && line.indexOf('"') != -1) {
                            StringBuilder currentLink = new StringBuilder();
                            int i = line.indexOf(URLDepthPair.URL_PREFIX);
                            while (line.charAt(i) != '"' && line.charAt(i) != ' ') {
                                currentLink.append(line.charAt(i));
                                i++;
                            }
                            //для каждого найдненного URL создаем новую пару
                            //и добавляем ее к пулу адресов, увеличивая глубину исходной пары на 1
                            URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.getDepth() + 1);
                            urlPool.addPair(newPair);
                        }
                        line = in.readLine();
                    }
//    @Override
//    public void run(){
//        while (true){
//            //здесь открывает сокет, передаем ему имя хоста, порт,
//            // время таймаута
//            URLDepthPair currentPair = urlPool.getPair();
//            try {
//                Socket my_socket;
//                try {
//                    my_socket = new Socket(currentPair.getHost(), 80);
//                } catch (UnknownHostException e) {
//                    System.out.println("Не удалось разрешить URL: "+currentPair.getURL()+" на depth "+currentPair.getDepth());
//                    continue;
//                }
//                my_socket.setSoTimeout(10000);
//                try {
//                    System.out.println("Сейчас сканируется: "+currentPair.getURL()+" на depth "+currentPair.getDepth());
//                    PrintWriter out = new PrintWriter(my_socket.getOutputStream(), true);
//                    BufferedReader in = new BufferedReader(new InputStreamReader(my_socket.getInputStream()));
//                    //делаем запрос
//                    request(out,currentPair);
//                    String line = in.readLine();
//                    System.out.println(line);
//                    while (line != null){
//                        if (line.contains(URLDepthPair.URL_PREFIX) && line.indexOf('"') != -1) {
//                            StringBuilder currentLink = new StringBuilder();
//                            int i = line.indexOf(URLDepthPair.URL_PREFIX);
//                            while (line.charAt(i) != '"' && line.charAt(i) != ' ') {
//                                currentLink.append(line.charAt(i));
//                                i++;
//                            }
//                            //для каждого найдненного URL создаем новую пару
//                            //и добавляем ее к пулу адресов, увеличивая глубину исходной пары на 1
//                            URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.getDepth() + 1);
//                            urlPool.addPair(newPair);
//                        }
//                        line = in.readLine();
//                    }





//                    String line;
////                    System.out.println(line);
//                    //проверяем, что юрл текущей ссылки содержит префикс и запускаем метод buildNewUrl,
//                    // добавляя новую пару к пулу юрл-адресов.
//                    while ((line = in.readLine()) != null){
//                        System.out.println(line);
//                        if (line.indexOf(URLDepthPair.URL_PREFIX)!=-1){
//                            buildNewUrl(line,currentPair.getDepth(),urlPool);
//                        }
//                    }




//                    String line = in.readLine();
//                    while (line != null){
//                        if (line.contains(URLDepthPair.URL_PREFIX) && line.indexOf('"') != -1) {
//                            StringBuilder currentLink = new StringBuilder();
//                            int i = line.indexOf(URLDepthPair.URL_PREFIX);
//                            while (line.charAt(i) != '"' && line.charAt(i) != ' ') {
//                                currentLink.append(line.charAt(i));
//                                i++;
//                            }
//                            //для каждого найдненного URL создаем новую пару
//                            //и добавляем ее к пулу адресов, увеличивая глубину исходной пары на 1
//                            URLDepthPair newPair = new URLDepthPair(currentLink.toString(), currentPair.getDepth() + 1);
//                            URLPool.addPair(newPair);
//                        }
//                        line = in.readLine();
//                    }




                    //закрываем сокет
                    socket.close();
                } catch (SocketTimeoutException e) {
                    socket.close();
                }
            }
            catch (IOException e) {}
        }
    }
}