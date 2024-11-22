import javax.swing.*;

public class TicTacToe {
    private JFrame frame;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private boolean isXTurn = true; // Turno inicial de X

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(null);

        // Etiqueta de estado
        statusLabel = new JLabel("Turno de X", SwingConstants.CENTER);
        statusLabel.setBounds(10, 10, 260, 30);
        frame.add(statusLabel);

        // Crear el tablero de botones
        buttons = new JButton[3][3];
        int size = 80;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setBounds(10 + j * size, 50 + i * size, size, size);
                buttons[i][j].setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
                buttons[i][j].addActionListener(e -> handleButtonClick((JButton) e.getSource()));
                frame.add(buttons[i][j]);
            }
        }

        // Botón para salir
        JButton exitButton = new JButton("Salir");
        exitButton.setBounds(100, 310, 80, 30);
        exitButton.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        frame.add(exitButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Manejar clic en un botón
    private void handleButtonClick(JButton button) {
        if (!button.getText().isEmpty()) return; // Ignorar si ya está ocupado

        button.setText(isXTurn ? "X" : "O"); // Colocar X o O
        if (checkWinner()) {
            statusLabel.setText("¡Ganó " + (isXTurn ? "X" : "O") + "!");
            disableButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("¡Empate!");
        } else {
            isXTurn = !isXTurn; // Cambiar turno
            statusLabel.setText("Turno de " + (isXTurn ? "X" : "O"));
        }
    }

    // Comprobar si hay un ganador
    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            // Revisar filas y columnas
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().isEmpty()) return true;
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().isEmpty()) return true;
        }
        // Revisar diagonales
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().isEmpty()) return true;
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().isEmpty()) return true;

        return false;
    }

    // Verificar si el tablero está lleno
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) return false;
            }
        }
        return true;
    }

    // Deshabilitar botones al terminar el juego
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
