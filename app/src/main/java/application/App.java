package application;

import java.awt.EventQueue;

import view.Start;

/**
 * The main class that starts the application.
 */
public final class App {

	/**
     * Private constructor to prevent instantiation
     */
    private App() {
    }
    
    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     * @throws Exception if an error occurs during application execution.
     */
    public static void main(final String[] args) throws Exception {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Start frame = new Start();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
