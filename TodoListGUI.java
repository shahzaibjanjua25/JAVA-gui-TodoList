
package oelsc;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class TodoListGUI extends JFrame {
    private JList<String> todoList;
    private JTextField newItemField;
    private TodoListLogic todoListLogic;

    public TodoListGUI() {
        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        setLayout(new BorderLayout());

        // Set UIManager for custom look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        todoListLogic = new TodoListLogic();

        // Create the to-do list
        todoList = new JList<>(todoListLogic.getTodoListModel());
        todoList.setFont(new Font("Arial", Font.PLAIN, 14));
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoList.setBorder(new EmptyBorder(10, 10, 10, 10));
        todoList.setBackground(new Color(240, 240, 240));
        todoList.setSelectionBackground(new Color(215, 215, 215));
        todoList.setFixedCellHeight(30);

        JScrollPane scrollPane = new JScrollPane(todoList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        add(scrollPane, BorderLayout.CENTER);

        // Create the new item input field
        newItemField = new JTextField();
        newItemField.setFont(new Font("Arial", Font.PLAIN, 14));
        newItemField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        newItemField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        // Create the "Add" button
        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(51, 153, 255));
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.setOpaque(true);
        addButton.setPreferredSize(new Dimension(80, 30));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        // Create the "Delete" button
    JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(51, 153, 255));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setOpaque(true);
        deleteButton.setPreferredSize(new Dimension(90, 30));


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });

        // Create panel for the button and input field
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create panel for the input field and buttons
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.add(newItemField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(textFieldPanel, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        // Create panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground((Color.BLACK));
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel("To-Do List by ByteBrusters");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(titlePanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addItem() {
        String newItem = newItemField.getText();
        todoListLogic.addItem(newItem);
        newItemField.setText("");
    }

    private void deleteItem() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex != -1) {
            String item = todoList.getSelectedValue();
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete '" + item + "'?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                todoListLogic.deleteItem(selectedIndex);
            }
        }
    }}