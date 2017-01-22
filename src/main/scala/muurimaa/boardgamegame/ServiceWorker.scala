package muurimaa.boardgamegame

import org.scalajs.dom
import org.scalajs.dom.experimental.serviceworkers._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.util.{Failure, Success}

@JSExport
object ServiceWorker {

  def serviceWorker = if(App.debug) "public/sw.js" else "sw.js"

  def init(): Unit = {
    if (!js.isUndefined(dom.window.navigator.serviceWorker)) {
      dom.window.navigator.serviceWorker.register(serviceWorker).toFuture.onComplete {
        case Success(reg) => println(s"Service worker registration succeeded: $reg")
        case Failure(err) => println(s"Service worker registration failed: $err")
      }
    } else println("Service workers not available.")
  }

//  @JSExport
//  def install(event: ExtendableEvent): Unit = {
//    val promise = ServiceWorkerGlobalScope.self.caches.open(cacheName)
//
//    promise.toFuture.onComplete {
//      case Success(cache) =>
//        cache.addAll(js.Array(
//          "./",
//          "./index.html",
//          "./manifest.json",
//          "./target/scala-2.11/pwascalajs-fastopt.js",
//          "./offline.html")).toFuture.onComplete {
//           case Success(_) =>
//             println(s"Service worker cache built")
//             ServiceWorkerGlobalScope.self.skipWaiting()
//        }
//      case Failure(err) => println(s"Service worker cache failed to open: $err")
//    }
//
//    event.waitUntil(promise)
//  }
//
//  // either respond with the cached object or go ahead and fetch the actual url
//  @JSExport
//  def fetchFromCache(event: FetchEvent): Promise[Unit] = {
//    println(s"Fetching ${event.request.url}")
//
//    val matchP: Promise[js.Any] = ServiceWorkerGlobalScope.self.caches.`match`(event.request)
//
//    matchP.toFuture.map { resp: js.Any =>
//      if(!js.isUndefined(resp)) {
//        println("Cache hit")
//        event.respondWith(resp.asInstanceOf[Response])
//      } else {
//        println("Cache miss")
//        event.respondWith(fetch(event.request))
//      }
//    }
//  }
}
