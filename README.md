[Dilan Rodriguez] Dilan-R
# Documentación del Sistema de Vidas y Puntuación

Este documento explica la funcionalidad y estructura de cada parte del sistema de **vidas y puntuación** dentro del proyecto del videojuego Rocket Space.

## 📦 Paquetes y Clases

### `core.PlayerStats` (Interfaz)
Define los métodos esenciales para el control de vidas y puntuación.

#### Métodos:
- `int getLives()` - Devuelve el número actual de vidas.
- `void loseLife()` - Disminuye una vida (si hay al menos una).
- `void addLife()` - Añade una vida.
- `int getScore()` - Devuelve el puntaje actual.
- `void addScore(int value)` - Incrementa el puntaje en una cantidad específica.
- `void reset()` - Reinicia las vidas y el puntaje.

---

### `core.PlayerStatsImpl` (Implementación)
Implementa la interfaz `PlayerStats`. Lleva un registro interno de las vidas y puntuación del jugador.

#### Atributos:
- `int lives` - Representa las vidas restantes del jugador.
- `int score` - Representa el puntaje acumulado.

#### Constructor:
- `PlayerStatsImpl(int initialLives)` - Inicializa el sistema con un número determinado de vidas.

#### Lógica:
- Cada vez que el jugador pierde una vida, `lives--` si es mayor que 0.
- Cada vez que el jugador gana puntos, `score += value`.
- El método `reset()` vuelve a poner las vidas en 3 y el puntaje en 0 (puede ajustarse).

---

### `ui.HUD`
Componente de interfaz gráfica que muestra la información al jugador (vidas y puntaje).

#### Atributos:
- `PlayerStats stats` - Instancia de estadísticas del jugador.

#### Métodos:
- `void draw(Graphics g)` - Muestra el número de vidas y puntaje actual en la pantalla con estilo personalizado.

---

### `controller.StatsController`
Controlador lógico del sistema. Encapsula la lógica del juego que modifica vidas y puntuación.

#### Atributos:
- `PlayerStats stats` - Referencia a la implementación del sistema de estadísticas.

#### Métodos:
- `void playerHit()` - El jugador pierde una vida.
- `void collectScore(int value)` - El jugador gana una cierta cantidad de puntos.
- `void gainLife()` - El jugador gana una vida.
- `void resetStats()` - Se reinicia el sistema.
- `int getCurrentLives()` / `getCurrentScore()` - Devuelven valores actuales.

---

### `main.GameScreen` (Integración)
Ejemplo de panel de juego que usa Swing para pintar el HUD en pantalla y simula eventos del juego.

#### Atributos:
- `StatsController controller` - Controlador del sistema.
- `HUD hud` - Elemento visual para mostrar vidas y puntuación.

#### Métodos:
- `paintComponent(Graphics g)` - Dibuja el HUD.
- `simulateGameplay()` - Simula una ronda de juego: suma puntos y resta una vida.

---