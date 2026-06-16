@echo off
chcp 65001 >nul
echo.
echo  +==========================================+
echo  ^|   Jogo da Forca - Iniciando...           ^|
echo  +==========================================+
echo.

if not exist "out\Main.class" (
    echo  [AVISO] Jogo nao compilado. Execute compilar.bat primeiro!
    pause
    exit /b 1
)

java -cp out Main
pause
