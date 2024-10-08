package ui.graphical.manage;

import model.Drinks;
import model.Order;


import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

//Represent the left panel for the Manage Order button
public class SelectDrinksPanel extends JPanel implements ListSelectionListener {
    private final JButton addDrinkButton;
    private final JButton deleteDrinkButton;
    private final AddDrinksPanel addDrinksPanel;
    private JScrollPane scrollPane;
    private JLabel picLabel;
    private Order selectedOrder = null;
    private Drinks selectedDrink = null;


    public SelectDrinksPanel(AddDrinksPanel addDrinksPanel) {
        setBackground(Color.DARK_GRAY);
        this.addDrinksPanel = addDrinksPanel;
        addDrinkButton = new JButton("Add Drinks");
        deleteDrinkButton = new JButton("Delete Drinks");
        JLabel drinksLabel = new JLabel("Drinks Ordered:");
        drinksLabel.setForeground(Color.WHITE);
        try {
            BufferedImage picture = ImageIO.read(new File("./data/Image.jpeg"));
            picLabel = new JLabel(new ImageIcon(picture));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error! Image File is Missing!");
        }

        add(addDrinkButton);
        add(deleteDrinkButton);
        add(picLabel);
        add(drinksLabel);

        setUpScrollPane();
        setUpAddDrinkActionListener();
        setUpDeleteDrinkActionListener();
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: set the selectedDrinks by the selected item on the list
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = (JList)e.getSource();
        selectedDrink = selectedOrder.getItemsOrdered().get(list.getSelectedIndex());
    }

    //MODIFIES: this
    //EFFECTS: update the drinks scroll pane based on items ordered in the order selected
    public void updateScrollPane() {
        JList<String> list;
        String[] names;
        ArrayList<Drinks> itemsOrdered = selectedOrder.getItemsOrdered();
        names = new String[itemsOrdered.size()];
        for (int i = 0; i < itemsOrdered.size(); i++) {
            names[i] = itemsOrdered.get(i).getClass().getTypeName().substring(6);
        }
        list = new JList<>(names);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionMode(0);
        list.addListSelectionListener(this);
        list.setFont(new Font(Font.DIALOG, Font.BOLD, 12));

        scrollPane.setViewportView(list);
    }

    //MODIFIES: this
    //EFFECTS: set up the scroll pane for drinks
    public void setUpScrollPane() {
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(130, 80));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        add(scrollPane);
    }

    //MODIFIES: this, addDrinksPanel
    //EFFECTS: set the selected Order, update the scroll pane and update the text shown on the right
    public void setUpAddDrinkActionListener() {
        addDrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedOrder != null) {
                    selectedOrder.addDrink(addDrinksPanel.getNewDrinksCreated());
                    updateScrollPane();
                    addDrinksPanel.getTextPane().setText(addDrinksPanel.updateOrderDetail());
                }
            }
        });
    }

    //EFFECTS: delete the order selected on the list
    public void setUpDeleteDrinkActionListener() {
        deleteDrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedDrink != null) {
                    selectedOrder.deleteDrink(selectedDrink);
                    updateScrollPane();
                    addDrinksPanel.getTextPane().setText(addDrinksPanel.updateOrderDetail());
                }
            }
        });
    }

    public void setSelectedOrder(Order order) {
        selectedOrder = order;
    }
}
