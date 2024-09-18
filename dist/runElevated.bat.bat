@echo off
:: Script para ejecutar un programa con privilegios elevados
setlocal

:: Obtiene la ruta del ejecutable
set EXE_PATH=%~1

:: Ejecuta el programa con privilegios elevados usando PowerShell
powershell -Command "Start-Process '%EXE_PATH%' -Verb RunAs"

endlocal
