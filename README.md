# cordova-plugin-system-gesture

This plugin avoids back gesture conflicts for gesture navigation in the 200dp range from the bottom left to the top of the screen.

## How To Use

```
document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
  cordova.plugins.systemGesture.setExclusionRects(
    function(result) { alert( "success: " + result ); },
    function(error) { alert( "error: " + error ); }
  );
}
```