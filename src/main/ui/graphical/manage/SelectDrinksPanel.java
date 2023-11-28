package ui.graphical.manage;

import model.Drinks;
import model.MilkTea;
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

public class SelectDrinksPanel extends JPanel implements ListSelectionListener {
    private JScrollPane scrollPane;
    private JButton addDrinkButton;
    private JButton deleteDrinkButton;
    private JLabel picLabel;
    private JLabel drinksLabel;
    private JList<String> list;
    private String[] names;
    private Order selectedOrder = null;
    private Drinks selectedDrinks = null;
    private AddDrinksPanel addDrinksPanel;

    public SelectDrinksPanel(AddDrinksPanel addDrinksPanel) {
        setBackground(Color.DARK_GRAY);
        this.addDrinksPanel = addDrinksPanel;
        addDrinkButton = new JButton("Add Drinks");
        deleteDrinkButton = new JButton("Delete Drinks");
        drinksLabel = new JLabel("Drinks Ordered:");
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

        Order order = new Order();
        order.addToOrdered(new MilkTea(50,50,"Medium"));
        order.addToOrdered(new Drinks(50, "Large"));
        setUpScrollPane();
        setUpAddDrinkActionListener();
        setUpDeleteDrinkActionListener();
        setVisible(true);
    }

    public void setUpScrollPane() {
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(130, 80));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        add(scrollPane);
    }

    public void updateScrollPane() {
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

        scrollPane.setColumnHeaderView(list);
        scrollPane.setVerticalScrollBar(new JScrollBar(JScrollBar.VERTICAL));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList)e.getSource();
        selectedDrinks = selectedOrder.getItemsOrdered().get(list.getSelectedIndex());
    }

    public void setSelectedOrder(Order order) {
        selectedOrder = order;
    }

    public void setUpAddDrinkActionListener() {
        addDrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedOrder != null) {
                    selectedOrder.addToOrdered(addDrinksPanel.getNewDrinksCreated());
                    updateScrollPane();
                    addDrinksPanel.getTextPane().setText(addDrinksPanel.updateOrderDetail());
                }
            }
        });
    }

    public void setUpDeleteDrinkActionListener() {
        deleteDrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedDrinks != null) {
                    selectedOrder.getItemsOrdered().remove(selectedDrinks);
                    updateScrollPane();
                }
            }
        });
    }

}
