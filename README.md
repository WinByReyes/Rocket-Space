# 🚀 Proyecto Java - Juego de Navecita (Versión Base con Movimiento WASD)

## 🎯 Objetivo

Desarrollar un juego básico en Java donde una nave espacial puede moverse libremente dentro de una ventana utilizando un **sprite personalizado**, es solo el comienzo para nuestro proycto final.

---

## 📁 Estructura del Proyecto

Rockect Space/

├── Sprites/

│ └── ShipSprite.png

├── src/

│ └── game/

│ ├── GameController.java

│ ├── GamePanel.java

│ └── Nave.java

├── App.java

---

- `src/game/`: contiene toda la lógica del juego.
- `Sprites/`: contiene el sprite de la nave cargado dinámicamente.

---

## 🧱 Componentes del Proyecto

### ✅ `App.java`

- Punto de entrada del programa.
- Mantiene solo la llamada a la clase `GameController` para delegar la inicialización.
- Esto mantiene el archivo limpio y enfocado.

### ✅ `GameController.java`

- Se encarga de crear la ventana (`JFrame`) del juego.
- Define el tamaño fijo (800x600) y el título.
- Agrega el `GamePanel` al frame.
- Inicia el juego llamando a `startGame()` del panel.

### ✅ `GamePanel.java`


- Es el panel principal del juego (`JPanel`) donde se dibujan los objetos y se ejecuta la lógica de actualización.
- Tiene un `Timer` para generar el ciclo de juego (aproximadamente 60 FPS).
- Maneja entrada por teclado y redibuja la pantalla.
- Contiene una instancia de `Nave`.

### ✅ `Nave.java`

- Representa la nave espacial.
- Soporta movimiento en las 4 direcciones usando teclas **W**, **A**, **S**, **D**. (Proximamente que las cambiaremos a los movimientos del mando, solo es de uso temporal)
- La nave no puede salirse del área visible del panel.
- Carga un sprite desde `Sprites/ShipSprite.png` para mostrar la imagen de la nave.
- Si la imagen no se encuentra o hay error, dibuja un rectángulo como respaldo.
- Las teclas son manejadas con `KeyEvent.VK_W`, etc., y los estados se actualizan por `keyPressed` y `keyReleased`.

## 🧠 Consideraciones Técnicas

- El sprite se carga con `ImageIO.read(new File(...))`, por lo que **debes ejecutar el programa desde la raíz del proyecto** para que funcione correctamente.
- Los límites del movimiento se controlan en `Nave.java` usando las dimensiones del panel.
- El `Timer` en `GamePanel` actualiza la lógica del juego cada ~16 ms.
- `GamePanel` y `Nave` están desacoplados para que se pueda extender fácilmente (por ejemplo, disparos, enemigos, colisiones, etc.).

---

## Explicaciones del codigo

**Darwin Reyes**

### 📄 GamePanel.java

``` Java
setPreferredSize(new Dimension(width,height));
setBackground(Color.BLACK);
addKeyListener(this);
```
- Configura el tamaño y el color de fondo del panel.
- También activa la detección de teclado (Proximamente para cambiar por el mando).

``` Java
Timer timer = new Timer(16, this);
```
- Inicia un "bucle de juego" que se ejecuta ~60 veces por segundo.
- Cada 16 ms, se actualiza la lógica y se repinta la pantalla.

## 📄 Nave.java

``` Java
BufferedImage sprite = ImageIO.read(new File("Sprites/ShipSprite.png"));
```
- Carga la imagen de la nave desde una carpeta externa llamada 'Sprites'.
- Si no se encuentra o falla, se usará un dibujo básico como respaldo.

``` Java
if (izquierda && x > 0) x -= velocidad;
if (derecha && x + ancho < limiteAncho) x += velocidad;
if (arriba && y > 0) y -= velocidad;
if (abajo && y + alto < limiteAlto) y += velocidad;
```
- Lógica de movimiento de la nave.
- Se asegura de que la nave no salga de los bordes del panel.
  
``` Java
public void dibujar(Graphics g) {
    g.drawImage(sprite, x, y, ancho, alto, null);
}
```
- Dibuja el sprite en la posición actual de la nave.

## Resumen - Darwin Reyes

 - Nave tiene posición, velocidad, sprite y dirección de movimiento.
 - GamePanel tiene un Timer que actualiza el juego constantemente.
 - GameLauncher crea la ventana y lanza el panel.
 - Main mantiene limpio el arranque del juego.

---
Para usar 
``` Java

```

``` Java

```

``` Java

```

``` Java

```