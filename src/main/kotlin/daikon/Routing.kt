package daikon

import daikon.Method.ANY

class Routing {
    private var defaultRoute = Route(ANY, "/*") { _, _ -> }
    private val routes = mutableListOf<Route>()

    fun default(route: Route): Routing {
        defaultRoute = route
        return this
    }

    fun bestFor(method: Method, path: String): Route {
        return filterBy(method)
            .filter { match(it, path) }
            .maxBy { it.path.length }
            ?:defaultRoute
    }

    private fun match(it: Route, path: String) = exact(it, path) || approximate(it, path)

    fun allFor(method: Method, path: String): List<Route> {
        return filterBy(method).filter { match(it, path) }
    }

    private fun filterBy(method: Method) = routes.filter { it.method == method || it.method == ANY }

    private fun approximate(route: Route, path: String): Boolean {
        return route.matches(path)
    }

    private fun exact(route: Route, path: String): Boolean {
        return route.path == path
    }

    fun add(route: Route) : Routing {
        routes.add(route)
        return this
    }
}