@echo off
chcp 65001
echo author_zzy time_%time%
adb remount

if "%1"=="reboot" goto :choice1
if "%1"=="back" goto :choice2
if "%1"=="+" goto :choice3
if "%1"=="-" goto :choice4

echo ===============================================
echo 1.reboot
echo 2.back
echo 3.VOL ++
echo 4.VOL --
echo 5.rotation 0
echo 6.rotation 1
echo 7.home
echo null.exit
echo ===============================================

:selectType
set choice=
set /p choice=Type the number of your choice:
if not '%choice%'=='' set choice=%choice:~0,1%
if /i '%choice%'=='1' goto:choice1
if /i '%choice%'=='2' goto:choice2
if /i '%choice%'=='3' goto:choice3
if /i '%choice%'=='4' goto:choice4
if /i '%choice%'=='5' goto:choice5
if /i '%choice%'=='6' goto:choice6
if /i '%choice%'=='7' goto:choice7
if /i '%choice%'=='' goto:close
echo "%choice%" is not a valid option. Please try again:
goto selectType

:choice1
adb shell su -c 'reboot'
goto:eof

:choice2
adb shell su -c 'input keyevent 4'
goto selectType

:choice3
adb shell su -c 'input keyevent 24'
goto selectType

:choice4
adb shell su -c 'input keyevent 25'
goto selectType

:choice5
adb shell su -c 'settings put system user_rotation 0'
goto selectType

:choice6
adb shell su -c 'settings put system user_rotation 1'
goto selectType

:choice7
adb shell su -c 'input keyevent 3'
goto selectType


:close
echo exit
goto:eof



