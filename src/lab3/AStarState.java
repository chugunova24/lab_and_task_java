package lab3;

import java.util.HashMap;

/**
 * Этот класс хранит базовое состояние, необходимое алгоритму A*
 * для вычисления пути по карте. Это состояние включает набор «открытых путевых точек»
 * и другой набор «закрытых путевых точек».
 * Кроме того, этот класс предоставляет основные операции,
 * необходимые алгоритму поиска пути A* для выполнения своей обработки.
 **/
public class AStarState
{
    /** Это ссылка на карту, по которой перемещается алгоритм A*.. **/
    private Map2D map;

    private HashMap<Location, Waypoint> openWaypoints = new HashMap<>();
    private HashMap<Location, Waypoint> closeWaypoints = new HashMap<>();

    /**
     * Инициализировать новый объект состояния для использования алгоритмом поиска пути A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Возвращает карту, по которой перемещается навигатор A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки и возвращает путевую точку
     * с минимальной общей стоимостью. Если открытых путевых точек нет, этот метод
     * возвращает <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (numOpenWaypoints() == 0) {
            return null;
        }

        Waypoint minWaypoint = null;
        float min = Float.MAX_VALUE;

        for (Waypoint waypoint : openWaypoints.values()) {
            float cost = waypoint.getTotalCost();
            if (cost < min) {
                min = cost;
                minWaypoint = waypoint;
            }
        }
        return minWaypoint;
    }

    /**
     * Этот метод добавляет путевую точку (или потенциально обновляет путевую точку,
     * уже находящуюся в ней) в коллекции «открытых путевых точек».
     * Если в местоположении новой путевой точки еще нет открытой путевой точки, новая
     * путевая точка просто добавляется в коллекцию. Однако, если в местоположении новой
     * путевой точки уже есть путевая точка, новая путевая точка заменяет старую,
     * <em>только если</em> значение «предыдущая стоимость» новой путевой точки меньше,
     * чем значение «предыдущая стоимость» текущей путевой точки.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint openWP = openWaypoints.get(newWP.loc);

        if (openWP == null || newWP.getPreviousCost() < openWP.getPreviousCost()) {
            openWaypoints.put(newWP.loc, newWP);
            return true;
        }
        return false;
    }


    /** Возвращает текущее количество открытых путевых точек. **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     * Этот метод перемещает путевую точку в указанном месте
     * из открытого списка в закрытый список.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = openWaypoints.remove(loc);
        if (openWaypoints != null) {
            closeWaypoints.put(loc, waypoint);
        }
    }

    /**
     * Возвращает true, если коллекция закрытых путевых точек
     * содержит путевую точку для указанного местоположения.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closeWaypoints.containsKey(loc);
    }
}

