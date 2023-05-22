/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oelsc;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TodoListLogic {
    private DefaultListModel<String> todoListModel;

    public TodoListLogic() {
        // Create the to-do list model
        todoListModel = new DefaultListModel<>();
    }

    public void addItem(String newItem) {
        if (newItem != null && !newItem.isEmpty()) {
            todoListModel.addElement(newItem);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid item.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteItem(int selectedIndex) {
        if (selectedIndex != -1) {
            todoListModel.remove(selectedIndex);
        }
    }

    public DefaultListModel<String> getTodoListModel() {
        return todoListModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TodoListGUI();
            }
        });
    }
}
