window.createEvent = function(eventParamsArr, callback) {
    cordova.exec(callback, function(err) {
        callback('Nothing to log.');
    }, "EventLogging", "createEvent", eventParamsArr);
};
