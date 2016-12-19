// use a cacheName for cache versioning
var cacheName = 'v1:static';

// during the install phase you usually want to cache static assets
self.addEventListener('install', function (e) {
    console.log('Service worker insted');
    // once the SW is installed, go ahead and fetch the resources to make this work offline
    e.waitUntil(
        caches.open(cacheName).then(function (cache) {
            console.log('SW cache open');
            return cache.addAll([
                './',
                './index.html',
                './manifest.json',
                './target/scala-2.11/pwascalajs-fastopt.js',
                './offline.html'
            ]).then(function () {
                self.skipWaiting();
            });
        })
    );
});

// when the browser fetches a url
self.addEventListener('fetch', function (event) {
    // either respond with the cached object or go ahead and fetch the actual url
    event.respondWith(
        caches.match(event.request).then(function (response) {
            if (response) {
                return response; // retrieve from cache
            }
            // fetch as normal
            return fetch(event.request);
        })
    );
});