// For cache versioning
var cacheName = 'v1:static';

self.addEventListener('install', function (e) {
    // once the SW is installed, go ahead and fetch the resources to make this work offline
    e.waitUntil(
        caches.open(cacheName).then(function (cache) {
            return cache.addAll([
                './',
                './index.html',
                './manifest.json',
                './boardgamegame-opt.js',
                './data.json',
                './sw.js',
                'https://fonts.googleapis.com/css?family=Montserrat',
                'bower_components/webcomponentsjs/webcomponents-lite.js',
                'bower_components/iron-icon/iron-icon.html',
                'bower_components/iron-icons/iron-icons.html'
            ]).then(function () {
                self.skipWaiting();
            });
        })
    );
});

// When the browser fetches a url
self.addEventListener('fetch', function (event) {
    // Either respond with the cached object or go ahead and fetch the actual url
    event.respondWith(
        caches.match(event.request).then(function (response) {
            if (response) {
                return response;
            }
            return fetch(event.request);
        })
    );
});
