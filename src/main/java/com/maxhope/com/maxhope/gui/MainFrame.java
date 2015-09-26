package com.maxhope.com.maxhope.gui;

import com.maxhope.game.Board;
import com.maxhope.game.Square;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private BoardPanel boardPanel = new BoardPanel();
    private StatsPanel statsPanel = new StatsPanel();
    private LeftPanel leftPanel = new LeftPanel();
    private BottomPanel bottomPanel = new BottomPanel();

    public MainFrame() {
        super("Chess");
        this.setResizable(false);
        this.setSize(new Dimension(700, 500));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.add(leftPanel, new GridBagConstraints(0, 0,
                1, 1,
                0, 0,
                GridBagConstraints.WEST,
                GridBagConstraints.BOTH,
                new Insets(0,0,0,0),
                0, 0));

        this.add(boardPanel, new GridBagConstraints(1, 0,
                1, 1,
                1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0,0,0,0),
                0, 0));

        this.add(bottomPanel, new GridBagConstraints(1, 1,
                1, 1,
                0, 0,
                GridBagConstraints.WEST,
                GridBagConstraints.BOTH,
                new Insets(0,0,0,0),
                0, 0));

        this.add(statsPanel, new GridBagConstraints(2, 0,
                1, 2,
                1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                new Insets(0,0,0,0),
                0, 0));

        statsPanel.setBorder(BorderFactory.createEtchedBorder());

        boardPanel.setLayout(new GridLayout(8, 8));
    }

    private class BoardPanel extends JPanel {

        private Board board = new Board();

        public BoardPanel() {

            this.setMinimumSize(new Dimension(500, MainFrame.this.getHeight()));
            this.setPreferredSize(new Dimension(500, MainFrame.this.getHeight()));

            for (int i = 7; i >= 0; i--) {
                for(int j = 0; j < 8; j++) {
                    Square square = board.getSquare(i, j);
                    SquarePanel squarePanel = new SquarePanel(square);
                    squarePanel.setBackground(square.getColor().getAWTColor());
                    this.add(squarePanel);
                }
            }
        }

        public Board getBoard() {
            return board;
        }
    }

    private class StatsPanel extends JPanel {

        private JLabel fromLabel = new JLabel("From");
        private JLabel toLabel = new JLabel("To");
        private Document fromLimitDocument = new PlainDocument() {
            public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
                if (str == null) return;

                if ((getLength() + str.length()) <= 2) {
                    super.insertString(offset, str, attr);
                }
            }
        };
        private Document toLimitDocument = new PlainDocument() {
            public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
                if (str == null) return;

                if ((getLength() + str.length()) <= 2) {
                    super.insertString(offset, str, attr);
                }
            }
        };
        private JTextField fromTextField = new JTextField();
        private JTextField toTextField = new JTextField();
        private JButton makeMoveButton = new JButton("Make move!");
        private JList<String> movesList = new JList<>();
        private JLabel errorLabel = new JLabel();

        public StatsPanel() {

            this.setLayout(new GridLayout(4, 1));
            JPanel textFieldsPanel = new JPanel();
            fromTextField.setColumns(2);
            fromTextField.setDocument(fromLimitDocument);
            toTextField.setColumns(2);
            toTextField.setDocument(toLimitDocument);

            textFieldsPanel.add(fromLabel);
            textFieldsPanel.add(fromTextField);
            textFieldsPanel.add(toLabel);
            textFieldsPanel.add(toTextField);

            errorLabel.setText("<html><font color='red'>Error!</font></html>");
            errorLabel.setVisible(false);

            movesList.setModel(new DefaultListModel());
            JScrollPane movesListScrollPane = new JScrollPane(movesList);

            this.add(textFieldsPanel);
            this.add(errorLabel);
            this.add(makeMoveButton);
            this.add(movesListScrollPane);

            makeMoveButton.addActionListener(new MoveButtonActionListener());
        }

        private class MoveButtonActionListener implements ActionListener {

            Board board = boardPanel.getBoard();

            @Override
            public void actionPerformed(ActionEvent e) {
                String from = fromTextField.getText();
                String to = toTextField.getText();

                if (from.isEmpty() || to.isEmpty()) {
                    errorLabel.setVisible(true);
                    return;
                }

                int[] fromPos = board.getPositionOnBoard(from);
                Square fromSquare = board.getSquare(fromPos[0], fromPos[1]);

                int[] toPos = board.getPositionOnBoard(to);
                Square toSquare = board.getSquare(toPos[0], toPos[1]);

                boolean moved = board.movePiece(fromSquare, toSquare);
                if (moved) {
                    DefaultListModel defaultListModel = (DefaultListModel)movesList.getModel();
                    defaultListModel.addElement(from + " to " + to);
                    errorLabel.setVisible(false);
                    boardPanel.repaint();
                } else {
                    errorLabel.setVisible(true);
                }

                int lastIndex = movesList.getModel().getSize()-1;
                if (lastIndex >= 0) {
                    movesList.ensureIndexIsVisible(lastIndex);
                }
            }
        }
    }

    private class LeftPanel extends JPanel {

        public LeftPanel() {
            this.setLayout(new GridLayout(8,1));
            for (int i = 8; i > 0; i--) {
                this.add(new JLabel("  "+Integer.toString(i)));
            }
        }
    }

    private class BottomPanel extends JPanel {

        public BottomPanel() {
            this.setLayout(new GridLayout(1,8));
            for (int i = 0; i < 8; i++) {
                this.add(new JLabel("     "+Character.toString((char)('A'+i))));
            }
        }
    }
}
