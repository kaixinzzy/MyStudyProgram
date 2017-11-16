@echo off

adb shell rm -rf mnt/sdcard/bclapp
adb shell mkdir -p mnt/sdcard/bclapp/
adb push ..\app\build\outputs\apk\app-debug.apk mnt/sdcard/bclapp/app.apk

adb shell su -c 'mount -o remount,rw /system'
adb shell su -c 'cp /mnt/sdcard/bclapp/* /system/priv-app/'
adb shell su -c 'chmod 755 /system/priv-app/app.apk'

goto:eof



