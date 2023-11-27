package ui.panel;

import model.ingredients.Ingredients;
import ui.OrderAppGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrinksPanel extends JSplitPane {
    JPanel drinkPanel;
    JPanel ingredientPanel;
    JSplitPane splitPane;
    JComboBox<String> types;
    JComboBox<String> iceLevel;
    JComboBox<String> sweetness;
    JComboBox<String> size;
    JLabel sweetnessLabel;
    String[] typesOfDrinks = {"MilkTea", "Drinks"};
    String[] sweetnessList = {"100", "80", "50", "20", "0"};
    String[] iceLevelList = {"100", "80", "50", "20", "0"};
    String[] sizeList = {"Medium", "Small", "Large"};
    JButton addButton;
    ArrayList<JCheckBox> checkBoxList;

    public DrinksPanel() {
        drinkPanel = new JPanel();
        ingredientPanel = new JPanel();
        types = new JComboBox<>(typesOfDrinks);
        sweetness = new JComboBox<>(sweetnessList);
        iceLevel = new JComboBox<>(iceLevelList);
        size = new JComboBox<>(sizeList);
        addButton = new JButton("add drink");
        checkBoxList = new ArrayList<>();

        setUpTopPane();

        setUpCheckBoxes();

        setVisible(true);
    }

    public void setUpTopPane() {
        setOrientation(JSplitPane.VERTICAL_SPLIT);
        setTopComponent(drinkPanel);
        setDividerLocation(85);
        drinkPanel.add(new JLabel("Drinks:"));
        drinkPanel.add(types);

        sweetnessLabel = new JLabel("Sweetness:");
        drinkPanel.add(sweetnessLabel);
        drinkPanel.add(sweetness);

        drinkPanel.add(new JLabel("Size:"));
        drinkPanel.add(size);

        drinkPanel.add(new JLabel("Ice Levels:"));
        drinkPanel.add(iceLevel);
    }

    public void setUpCheckBoxes() {
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        splitPane.setDividerLocation(OrderAppGUI.FRAME_WIDTH / 3);
        splitPane.setVisible(true);
        JLabel text = new JLabel("Select Toppings:");
        left.add(text, BorderLayout.WEST);

        for (Ingredients ingredients : Ingredients.values()) {
            JCheckBox checkBox = new JCheckBox(ingredients.name());
            checkBoxList.add(checkBox);
            left.add(checkBox, BorderLayout.CENTER);
        }
        drinkPanel.add(addButton, BorderLayout.SOUTH);
        //ingredientPanel.add(new JLabel("Order details:"), BorderLayout.SOUTH);
        JEditorPane j = new JEditorPane();
        right.add(j);
        j.setContentType("test/plain");
        j.setPreferredSize(new Dimension(300, 300));
        j.setText(getOrderDetail());

        j.setEditable(false);
        setBottomComponent(splitPane);
    }

    public String getOrderDetail() {
        String text = "Order detail:\n";

        return text;
    }

    public ArrayList<Ingredients> getSelectedToppings() {
        ArrayList<Ingredients> array = new ArrayList<>();

        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isSelected()) {
                array.add(Ingredients.values()[i]);
            }
        }

        return array;
    }
}
