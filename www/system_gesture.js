var exec = require('cordova/exec');

var PLUGIN_NAME = 'SystemGesturePlugin';
var pluginMethod = {
    setExclusionRects: 'setExclusionRects'
};

var systemGesture = {
    setExclusionRects: function(success, error) {
        exec(success, error, PLUGIN_NAME, pluginMethod.setExclusionRects, []);
    }
};

module.exports = systemGesture;
