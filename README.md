# My Personal Project

## An ordered system for bubble tea shop

The application empowers employees to **manage orders** and **oversee related tasks** within a bubble tea shop. 
My passion for bubble tea inspired this initiative, as I believe such an application *streamlines operations* 
and enhances *user experience*.


## User Stories:
- As a user, I want to be able to add drinks to an order and specifying their types and desired customization.
- As a user, I want to be able to display all the drinks in an order with their types and desired customizations after the order is added.
- As a user, I want to be able to add toppings to drinks after I add them to an order.
- As a user, I want to be able to display the toppings I added after I add drinks.
- As a user, when I close the application, I want to be able to save the orders to files.
- As a user, when I start the application, I want to be given the option to load my previous orders from files.

# Instructions for Grader

**You can generate the first required action related to the user story "adding multiple Xs to a Y" by:**
1.Click on the button Show All Orders on the top.
2.Click on Add Order and select the order added.
3.Click on the button Manage Order
4.Select the preferred customization for you drink and click on Add Drink button.

**You can generate the second required action related to the user story "adding multiple Xs to a Y" by:**
1.Click on the button Show All Orders on the top.
2.Click on Add Order and select the order added.
3.Click on the button Manage Order
4.Select the preferred customization for you drink and click on Delete Drink button.

**You can generate the displayed all Xs in Y action by:**
Repeating the instruction for step 4 multiple times and each time Xs will be displayed on the right panel.

**You can locate my visual component by clicking on Manage Order.**

**You can save the state of my application by clicking on the Menu button located on the top-left of the screen
and click on save.**

**You can reload the state of my application by clicking on the Menu and load.**


# Phase 4: Task 2
Tue Nov 28 22:15:34 PST 2023
order added to order history.

Tue Nov 28 22:15:35 PST 2023
Order details displayed.

Tue Nov 28 22:15:40 PST 2023
MilkTea added to order.

Tue Nov 28 22:15:40 PST 2023
Order details displayed.

Tue Nov 28 22:15:42 PST 2023
Drinks added to order.

Tue Nov 28 22:15:42 PST 2023
Order details displayed.

Tue Nov 28 22:15:45 PST 2023
MilkTea deleted from order.

Tue Nov 28 22:15:51 PST 2023
Drink at 0 removed from order.


# Phase 4: Task 3
The refactoring I might use to improve my design is to make OrderHistoryPanel, AddDrinksPanel and
SelectDrinksPanel to communicate through OrderAppGUI so that the relationship can be less complicated and
easy to maintain the relationship in future updates.

