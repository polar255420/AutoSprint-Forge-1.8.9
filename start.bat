@echo off
REM ============================================
REM  Script para compilar proyecto con Gradle
REM  Usando Java 8 (Zulu)
REM ============================================

REM Configurar JAVA_HOME para usar Zulu 8
set "JAVA_HOME=C:\Program Files\Zulu\zulu-8"
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Verificar que Java está disponible
echo Verificando version de Java...
java -version
echo.

REM Ejecutar gradlew build
echo Ejecutando gradlew.bat build...
call gradlew.bat build

REM Verificar resultado
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] La compilacion fallo con codigo %ERRORLEVEL%
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo [OK] Compilacion completada exitosamente
pause