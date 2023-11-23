package ui.panel;

import model.Drinks;
import model.MilkTea;
import model.Order;

import ui.OrderAppGUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class OrderPanel extends JPanel implements ListSelectionListener {
    JButton button;
    JButton button1;
    JLabel picLabel;
    JLabel drinksLabel;
    JList<String> list;
    String[] names;

    public OrderPanel() {
        setPreferredSize(new Dimension(OrderAppGUI.FRAME_WIDTH / 5, OrderAppGUI.FRAME_HEIGHT / 10));
        //setBorder(new Border(OrderAppGUI.FRAME_WIDTH/10, OrderAppGUI.FRAME_HEIGHT/10));
        setBackground(Color.DARK_GRAY);
        button = new JButton("Add Drinks");
        button1 = new JButton("Delete Drinks");
        drinksLabel = new JLabel("Drinks Ordered:");
        drinksLabel.setForeground(Color.WHITE);
        try {
            BufferedImage picture = ImageIO.read(new File("./data/Image.jpeg"));
            picLabel = new JLabel(new ImageIcon(picture));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error! Image File is Missing!");
        }

        add(button);
        add(button1);
        add(picLabel);
        add(drinksLabel);

        Order order = new Order();
        order.addToOrdered(new MilkTea(50,50,"Medium"));
        order.addToOrdered(new Drinks(50, "Large"));
        addJScrollPane(order.getItemsOrdered());
    }

    public void addJScrollPane(ArrayList<Drinks> itemsOrdered) {
        names = new String[itemsOrdered.size()];
        for (int i = 0; i < itemsOrdered.size(); i++) {
            names[i] = itemsOrdered.get(i).getClass().getTypeName().substring(6);
        }
        list = new JList<>(names);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectionMode(0);
        list.addListSelectionListener(this);
        list.setFont(new Font(Font.DIALOG, Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(130, 80));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.ORANGE));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        add(scrollPane);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.drawRect(JPanel.WIDTH, JPanel.HEIGHT, JPanel.WIDTH / 4, JPanel.HEIGHT / 4);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
