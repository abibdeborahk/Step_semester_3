class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode,
                    String routeName,
                    int priority) {

        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode,
                    String routeName) {

        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }

        int nameCompare =
                this.routeName.compareToIgnoreCase(other.routeName);

        if (nameCompare != 0) {
            return nameCompare;
        }

        int codeCompare =
                this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeCompare != 0) {
            return codeCompare;
        }

        return 0;
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        for (int i = 1; i < routes.length; i++) {

            BusRoute current = routes[i];

            int j = i - 1;

            while (j >= 0 &&
                   routes[j].compareTo(current) > 0) {

                routes[j + 1] = routes[j];
                j--;
            }

            routes[j + 1] = current;
        }

        return routes;
    }

    public String getRouteCode() {
        return routeCode;
    }
}

public class BusRouteRankingEngine {

    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute.rankRoutes(routes);

        for (BusRoute route : routes) {
            System.out.println(route.getRouteCode());
        }
    }
}