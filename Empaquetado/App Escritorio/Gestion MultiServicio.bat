@echo off
java -version 2>nul
if %errorlevel% neq 0 (
    powershell -command "Add-Type -AssemblyName PresentationCore,PresentationFramework;[System.Windows.MessageBox]::Show('Java no esta instalado en este sistema. Por favor, instale Java desde https://www.java.com/es/download/', 'Error', 'OK', 'Error')"
    exit /b 1
) else (
    java -jar gms2_app.jar
)
pause
