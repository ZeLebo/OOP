package ru.nsu.sartakov.order;

public class Order {
    private static int nextId = 0;
    private final int id;
    private final String pizza;
    public Status status;

    /**
     * Order Status possible combinations
     */
    public enum Status {
        PENDING {
            /**
             * Return the Status of order
             * @return Pending status
             */
            @Override
            public String toString() {
                return "Pending";
            }
        },
        COOKING {
            /**
             * Return the Status of order
             * @return Cooking status
             */
            @Override
            public String toString() {
                return "Cooking";
            }
        },
        COOKED {
            /**
             * Return the Status of order
             * @return Cooked status
             */
            @Override
            public String toString() {
                return "Cooked";
            }
        },
        DELIVERING {
            /**
             * Return the Status of order
             * @return Delivering status
             */
            @Override
            public String toString() {
                return "Delivering";
            }
        },
        DELIVERED {
            /**
             * Return the Status of order
             * @return Delivered status
             */
            @Override
            public String toString() {
                return "Delivered";
            }
        }
    }

    /**
     * Constructor for Order
     * @param pizza - naming of pizza
     */
    public Order(String pizza) {
        this.id = nextId++;
        this.pizza = pizza;
    }

    /**
     * Returns the pizza naming
     * @return pizza
     */
    public String getPizza() {
        return this.pizza;
    }

    /**
     * Return the Order id
     * @return id
     */
    public int id() {
        return this.id;
    }

    /**
     * Method to get Order status
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Method to update the state of order
     * @param newStatus - new status
     */
    public void setStatus(Status newStatus) {
        this.status = newStatus;
        System.out.println(this);
    }

    /**
     * Method for representing Order data in String
     * @return Order data
     */
    @Override
    public String toString() {
        return "[Order id: " + id + "]" + "[Pizza: " + getPizza() + "]" +  "[" + status.toString() + "]";
    }
}
