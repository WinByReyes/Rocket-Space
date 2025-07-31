package input;

import MainMenu.MenuPanel;
import game.*;
import net.java.games.input.*;

public class GamepadManager {
    private Controller gamepad;
    private boolean esBotonDisparoPresionado = false;
    private boolean esBotonPausaPresionado = false;
    boolean esJoystickArriba = false;
    private boolean esJoystickAbajo = false;

public GamepadManager() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK) {
                gamepad = c;
                System.out.println("Mando detectado: " + c.getName());
                break;
            }
        }
    }


    public void actualizar(Nave nave, GamePanel panel) {
        if (gamepad == null) return;

        gamepad.poll();
        EventQueue queue = gamepad.getEventQueue();
        Event event = new Event();

        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            float valor = event.getValue();
            String nombre = comp.getName();

            switch (nombre) {
                case "X Axis" -> {
                    if (valor < -0.5f) {
                        nave.setIzquierda(true);
                        nave.setDerecha(false);
                    } else if (valor > 0.5f) {
                        nave.setDerecha(true);
                        nave.setIzquierda(false);
                    } else {
                        nave.setIzquierda(false);
                        nave.setDerecha(false);
                    }
                }

                case "Y Axis" -> {
                    if (valor < -0.5f) {
                        nave.setArriba(true);
                        nave.setAbajo(false);
                    } else if (valor > 0.5f) {
                        nave.setAbajo(true);
                        nave.setArriba(false);
                    } else {
                        nave.setArriba(false);
                        nave.setAbajo(false);
                    }
                }

                case "Button 0" -> {
                    if (valor == 1.0f && !esBotonDisparoPresionado) {
                        nave.dispararDesdeMando();
                        esBotonDisparoPresionado = true;
                    } else if (valor == 0.0f) {
                        esBotonDisparoPresionado = false;
                    }
                }

                case "Button 9" -> { // Botón Start para pausar
                    if (valor == 1.0f && !esBotonPausaPresionado) {
                        panel.mostrarMenuPausa(); // necesitas exponer este método
                        esBotonPausaPresionado = true;
                    } else if (valor == 0.0f) {
                        esBotonPausaPresionado = false;
                    }
                }
            }
        }
    }

     public void actualizarMenu(MenuPanel menuPanel) {
        if (gamepad == null) return;

        gamepad.poll();
        EventQueue queue = gamepad.getEventQueue();
        Event event = new Event();

        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            float value = event.getValue();
            String name = comp.getName();

            if ("Y Axis".equals(name)) {
                if (value < -0.5f && !esJoystickArriba) {
                    menuPanel.moverSeleccion(-1);
                    esJoystickArriba = true;
                } else if (value > 0.5f && !esJoystickAbajo) {
                    menuPanel.moverSeleccion(1);
                    esJoystickAbajo = true;
                } else if (Math.abs(value) < 0.2f) {
                    esJoystickArriba = false;
                    esJoystickAbajo = false;
                }
            }

            if ("Button 0".equals(name) && value == 1.0f && !esBotonDisparoPresionado) {
                menuPanel.activarSeleccion();
                esBotonDisparoPresionado = true;
            }

            if ("Button 0".equals(name) && value == 0.0f) {
                esBotonDisparoPresionado = false;
            }package input;

import MainMenu.MenuPanel;
import game.*;
import net.java.games.input.*;

public class GamepadManager {
    private Controller gamepad;
    private boolean botonDisparoPresionado = false;
    private boolean botonPausaPresionado = false;
      boolean joystickArriba = false;
    private boolean joystickAbajo = false;

public GamepadManager() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK) {
                gamepad = c;
                System.out.println("Mando detectado: " + c.getName());
                break;
            }
        }
    }


    public void actualizar(Nave nave, GamePanel panel) {
        if (gamepad == null) return;

        gamepad.poll();
        EventQueue queue = gamepad.getEventQueue();
        Event event = new Event();

        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            float valor = event.getValue();
            String nombre = comp.getName();

            switch (nombre) {
                case "X Axis" -> {
                    if (valor < -0.5f) {
                        nave.setIzquierda(true);
                        nave.setDerecha(false);
                    } else if (valor > 0.5f) {
                        nave.setDerecha(true);
                        nave.setIzquierda(false);
                    } else {
                        nave.setIzquierda(false);
                        nave.setDerecha(false);
                    }
                }

                case "Y Axis" -> {
                    if (valor < -0.5f) {
                        nave.setArriba(true);
                        nave.setAbajo(false);
                    } else if (valor > 0.5f) {
                        nave.setAbajo(true);
                        nave.setArriba(false);
                    } else {
                        nave.setArriba(false);
                        nave.setAbajo(false);
                    }
                }

                case "Button 0" -> {
                    if (valor == 1.0f && !botonDisparoPresionado) {
                        nave.dispararDesdeMando();
                        botonDisparoPresionado = true;
                    } else if (valor == 0.0f) {
                        botonDisparoPresionado = false;
                    }
                }

                case "Button 9" -> { // Botón Start para pausar
                    if (valor == 1.0f && !botonPausaPresionado) {
                        panel.mostrarMenuPausa(); // necesitas exponer este método
                        botonPausaPresionado = true;
                    } else if (valor == 0.0f) {
                        botonPausaPresionado = false;
                    }
                }
            }
        }
    }

     public void actualizarMenu(MenuPanel menuPanel) {
        if (gamepad == null) return;

        gamepad.poll();
        EventQueue queue = gamepad.getEventQueue();
        Event event = new Event();

        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            float value = event.getValue();
            String name = comp.getName();

            if ("Y Axis".equals(name)) {
                if (value < -0.5f && !joystickArriba) {
                    menuPanel.moverSeleccion(-1);
                    joystickArriba = true;
                } else if (value > 0.5f && !joystickAbajo) {
                    menuPanel.moverSeleccion(1);
                    joystickAbajo = true;
                } else if (Math.abs(value) < 0.2f) {
                    joystickArriba = false;
                    joystickAbajo = false;
                }
            }

            if ("Button 0".equals(name) && value == 1.0f && !botonDisparoPresionado) {
                menuPanel.activarSeleccion();
                botonDisparoPresionado = true;
            }

            if ("Button 0".equals(name) && value == 0.0f) {
                botonDisparoPresionado = false;
            }
        }
    }
}

