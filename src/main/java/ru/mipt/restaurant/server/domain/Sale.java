package ru.mipt.restaurant.server.domain;

public class Sale {

    private int amount;
    private String description;

    public Sale() {
    }

    public Sale(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        if (amount != sale.amount) return false;
        return description != null ? description.equals(sale.description) : sale.description == null;
    }

    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
