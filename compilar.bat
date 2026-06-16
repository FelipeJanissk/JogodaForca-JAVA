@echo off
chcp 65001 >nul
echo.
echo  +==========================================+
echo  ^|   Jogo da Forca - Compilador             ^|
echo  +==========================================+
echo.

if not exist "out" mkdir out

echo  Compilando arquivos Java...
javac -encoding UTF-8 -d out src\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo  [OK] Compilacao concluida com sucesso!
    echo  [OK] Execute "executar.bat" para jogar.
    echo.
) else (
    echo.
    echo  [ERRO] Falha na compilacao. Verifique os erros acima.
    echo.
)
pause
