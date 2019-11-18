package daikon

import javax.servlet.http.HttpServletRequest

class HttpRequest(private val request: HttpServletRequest, private val pathParams: PathParams) : Request {
    override fun body(): String {
        return request.reader.readText()
    }

    override fun header(name: String): String {
        return request.getHeader(name)
    }

    override fun param(name: String): String? {
         return request.getParameter(name) ?: pathParams.valueOf(request.requestURI)[name]
    }
}