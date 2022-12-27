package lab8;

import java.util.LinkedList;
import java.net.MalformedURLException;
import java.net.URL;
public class URLDepthPair {

    public final static String URL_PREFIX = "http://";
    public String URL;
    public int depth;

    public URLDepthPair (String URL, int depth) {
        this.URL = URL;
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public String getUrl() {
        return URL;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setUrl(String URL) {
        this.URL = URL;
    }

    public String getHost() throws MalformedURLException {
        URL host = new URL(URL);
        return host.getHost();
    }
    public String getPath() throws MalformedURLException {
        URL path = new URL(URL);
        return path.getPath();
    }

    @Override
    public String toString() {
        return "URLDepthPair{" +
                "url='" + URL + '\'' +
                ", depth=" + depth +
                '}';
    }

    public static boolean check(LinkedList<URLDepthPair> viewedLinks, URLDepthPair pair) {
        boolean isAlready = true;
        for (URLDepthPair i : viewedLinks)
            if (i.getUrl().equals(pair.getUrl()))
                isAlready=false;
        return isAlready;
    }





//    public static final String URL_PREFIX = "http://";
//    public String URL;
//    private int depth;
//    URL host_path;
//    public URLDepthPair (String URL, int depth){
//        this.URL=URL;
//        this.depth=depth;
//        try {
//            this.host_path= new URL(URL);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //get method for host
//    public String getHost() throws MalformedURLException {
//        URL host = new URL(URL);
//        return host.getHost();
//    }
//    public String getPath() throws MalformedURLException {
//        URL path = new URL(URL);
//        return path.getPath();
//    }
//
//    //get method for depth
//    public int getDepth() {
//        return depth;
//    }
//
//    //get method for full url
//    public String getURL() {
//        return URL;
//    }
//
//    //проверка на уже посещенную страницу
//    public static boolean check(LinkedList<URLDepthPair> resultLink, URLDepthPair pair) {
//        boolean isAlready = true;
//        for (URLDepthPair c : resultLink)
//            if (c.getURL().equals(pair.getURL()))
//                isAlready=false;
//        return isAlready;
//    }
}